/*
    Seam
    Contributor(s): dannytaylor
    Github: https://github.com/Perspective-Viewpoint/seam
    Licence: GNU LGPLv3
*/

package dev.dannytaylor.perspective.seam.client.events;

import com.mojang.blaze3d.resource.GraphicsResourceAllocator;
import dev.dannytaylor.perspective.seam.client.SeamClient;
import dev.dannytaylor.perspective.seam.common.data.log.LogMessage;
import dev.dannytaylor.perspective.seam.common.events.SeamExecute;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.debug.DebugScreenEntryStatus;
import net.minecraft.client.gui.components.debug.DebugScreenProfile;
import net.minecraft.client.input.MouseButtonInfo;
import net.minecraft.resources.Identifier;
import net.minecraft.server.packs.resources.ReloadableResourceManager;
import org.joml.Vector2i;

import java.util.*;

public class SeamClientExecute extends SeamExecute {
    public static Optional<IconOverride> getIconOverride(String modId) {
        List<IconOverride> iconOverrides = SeamClientEvents.IconOverrides.get(modId);
        if (iconOverrides != null) {
            for (IconOverride iconOverride : iconOverrides.stream().sorted(Comparator.comparing((iconOverride) -> iconOverride.getIconId().toString(), String.CASE_INSENSITIVE_ORDER)).toList()) {
                if (iconOverride.shouldOverride()) return Optional.of(iconOverride);
            }
        }
        return Optional.empty();
    }

    public static Map<Identifier, DebugScreenEntryStatus> getProfiledDebugEntries(DebugScreenProfile profile) {
        Map<Identifier, DebugScreenEntryStatus> profiles = new HashMap<>();
        SeamClientEvents.ProfiledDebugEntries.forEach((id, entry) -> {
            if (entry.profile().equals(profile)) profiles.put(id, entry.status());
        });
        return profiles;
    }

    public static void registerClientResourceReloaders(ReloadableResourceManager resourceManager) {
        SeamClientEvents.ClientResourceReloaders.forEach((id, resourceReloader) -> resourceManager.registerReloadListener(resourceReloader));
    }

    public static void afterClientResourceReload() {
        SeamClientEvents.AfterClientResourceReload.forEach((id, runnable) -> runnable.run());
    }

    public static void beforeGuiRender(GuiGraphics guiGraphics, DeltaTracker renderTickCounter) {
        SeamClientEvents.BeforeGuiRender.forEach(((id, runnable) -> {
            SeamClientEvents.tryRun(SeamClient.getMod(), () -> runnable.run(guiGraphics, renderTickCounter), input -> new LogMessage("Failed to execute BeforeGuiRender event with id: {}", id));
        }));
    }

    public static void afterGuiRender(GuiGraphics guiGraphics, DeltaTracker renderTickCounter) {
        SeamClientEvents.AfterGuiRender.forEach(((id, runnable) -> SeamClientEvents.tryRun(SeamClient.getMod(), () -> {
            runnable.run(guiGraphics, renderTickCounter);
        }, input -> new LogMessage("Failed to execute AfterGuiRender event with id: {}", id))));
    }

    public static void beforeGameRender() {
        SeamClientEvents.BeforeGameRender.forEach(((id, runnable) -> SeamClientEvents.tryRun(SeamClient.getMod(), runnable, input -> new LogMessage("Failed to execute BeforeGameRender event with id: {}", id))));
    }

    public static void afterPanoramaRender(GuiGraphics guiGraphics, int width, int height, boolean rotate, GraphicsResourceAllocator allocator) {
        SeamClientEvents.AfterPanoramaRender.forEach(((id, runnable) -> SeamClientEvents.tryRun(SeamClient.getMod(), () -> {
            runnable.run(guiGraphics, width, height, rotate, new SeamClientRunnables.RenderData(SeamClient.getMinecraft().getMainRenderTarget(), allocator));
        }, input -> new LogMessage("Failed to execute AfterPanoramaRender event with id: {}", id))));
    }

    public static void afterVanillaPostEffectRender(GraphicsResourceAllocator allocator) {
        SeamClientEvents.AfterVanillaPostEffectRender.forEach(((id, runnable) -> SeamClientEvents.tryRun(SeamClient.getMod(), () -> {
            runnable.run(new SeamClientRunnables.RenderData(SeamClient.getMinecraft().getMainRenderTarget(), allocator));
        }, input -> new LogMessage("Failed to execute AfterVanillaPostEffectRender event with id: {}", id))));
    }

    public static void beforeUiRender(GraphicsResourceAllocator allocator) {
        SeamClientEvents.BeforeUiRender.forEach(((id, runnable) -> SeamClientEvents.tryRun(SeamClient.getMod(), () -> {
            runnable.run(new SeamClientRunnables.RenderData(SeamClient.getMinecraft().getMainRenderTarget(), allocator));
        }, input -> new LogMessage("Failed to execute BeforeUiRender event with id: {}", id))));
    }

    public static void afterUiBackgroundRender(GraphicsResourceAllocator allocator) {
        SeamClientEvents.AfterUiBackgroundRender.forEach(((id, runnable) -> SeamClientEvents.tryRun(SeamClient.getMod(), () -> {
            runnable.run(new SeamClientRunnables.RenderData(SeamClient.getMinecraft().getMainRenderTarget(), allocator));
        }, input -> new LogMessage("Failed to execute AfterUiBackgroundRender event with id: {}", id))));
    }

    public static void afterUiRender(GraphicsResourceAllocator allocator) {
        SeamClientEvents.AfterUiRender.forEach(((id, runnable) -> SeamClientEvents.tryRun(SeamClient.getMod(), () -> {
            runnable.run(new SeamClientRunnables.RenderData(SeamClient.getMinecraft().getMainRenderTarget(), allocator));
        }, input -> new LogMessage("Failed to execute AfterUiRender event with id: {}", id))));
    }

    public static void resize(int width, int height) {
        SeamClientEvents.OnResized.forEach(((id, runnable) -> SeamClientEvents.tryRun(SeamClient.getMod(), () -> {
            runnable.run(width, height);
        }, input -> new LogMessage("Failed to execute OnResized event with id: {}", id))));
    }

    public static boolean onMouseScroll(long windowHandle, double horizontal, double vertical, Vector2i scroll) {
        boolean shouldCancel = false;
        for (Identifier registry : SeamClientEvents.OnMouseScroll.keySet()) {
            if (SeamClientEvents.OnMouseScroll.get(registry).call(windowHandle, horizontal, vertical, scroll)) shouldCancel = true;
        }
        return shouldCancel;
    }

    public static boolean onMouseButton(long windowHandle, MouseButtonInfo mouseButtonInfo, @MouseButtonInfo.Action int action) {
        boolean shouldCancel = false;
        for (Identifier registry : SeamClientEvents.OnMouseButton.keySet()) {
            if (SeamClientEvents.OnMouseButton.get(registry).call(windowHandle, mouseButtonInfo, action)) shouldCancel = true;
        }
        return shouldCancel;
    }

    public static void onJoinWorld() {
        SeamClientEvents.OnJoinWorld.forEach(((id, runnable) -> SeamClientEvents.tryRun(SeamClient.getMod(), runnable, input -> new LogMessage("Failed to execute OnJoinWorld event with id: {}", id))));
    }

    public static void onLeaveWorld() {
        SeamClientEvents.OnLeaveWorld.forEach(((id, runnable) -> SeamClientEvents.tryRun(SeamClient.getMod(), runnable, input -> new LogMessage("Failed to execute OnLeaveWorld event with id: {}", id))));
    }
}
