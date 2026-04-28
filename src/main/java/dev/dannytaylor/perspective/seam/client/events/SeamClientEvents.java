/*
    Seam
    Contributor(s): dannytaylor
    Github: https://github.com/Perspective-Viewpoint/seam
    Licence: GNU LGPLv3
*/

package dev.dannytaylor.perspective.seam.client.events;

import dev.dannytaylor.perspective.seam.client.SeamClient;
import dev.dannytaylor.perspective.seam.common.data.AbstractMod;
import dev.dannytaylor.perspective.seam.common.events.SeamEvents;
import dev.dannytaylor.perspective.seam.common.events.registries.HashRegistry;
import dev.dannytaylor.perspective.seam.common.events.registries.Registry;
import dev.dannytaylor.perspective.seam.common.util.Components;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.components.debug.DebugScreenEntries;
import net.minecraft.client.gui.components.debug.DebugScreenEntry;
import net.minecraft.client.gui.components.debug.DebugScreenEntryStatus;
import net.minecraft.client.gui.components.debug.DebugScreenProfile;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.server.packs.resources.PreparableReloadListener;

import java.util.*;

@Environment(EnvType.CLIENT)
public class SeamClientEvents extends SeamEvents {
    public static final Registry<CustomBadge> CustomBadges = new Registry<>();
    public static final HashRegistry<String, List<Identifier>> ModBadges = new HashRegistry<>();
    public static final HashRegistry<String, List<IconOverride>> IconOverrides = new HashRegistry<>();

    public static final Registry<ProfiledEntry> ProfiledDebugEntries = new Registry<>();

    public static final Registry<PreparableReloadListener> ClientResourceReloaders = new Registry<>();
    public static final Registry<Runnable> AfterClientResourceReload = new Registry<>();

    public static final Registry<SeamClientRunnables.GuiRender> BeforeGuiRender = new Registry<>();
    public static final Registry<SeamClientRunnables.GuiRender> AfterGuiRender = new Registry<>();

    public static final Registry<Runnable> BeforeGameRender = new Registry<>();
    public static final Registry<SeamClientRunnables.PanoramaRender> AfterPanoramaRender = new Registry<>();
    public static final Registry<SeamClientRunnables.GameRender> AfterVanillaPostEffectRender = new Registry<>();
    public static final Registry<SeamClientRunnables.GameRender> BeforeUiRender = new Registry<>();
    public static final Registry<SeamClientRunnables.GameRender> AfterUiBackgroundRender = new Registry<>();
    public static final Registry<SeamClientRunnables.GameRender> AfterUiRender = new Registry<>();

    public static final Registry<SeamClientRunnables.OnResized> OnResized = new Registry<>();

    public static final Registry<SeamClientCallables.OnMouseScroll> OnMouseScroll = new Registry<>();
    public static final Registry<SeamClientCallables.OnMouseButton> OnMouseButton = new Registry<>();

    public static final Registry<Runnable> OnJoinWorld = new Registry<>();
    public static final Registry<Runnable> OnLeaveWorld = new Registry<>();

    private static final MessageBar messageBar = new MessageBar();

    public static Identifier registerCustomBadge(Identifier badgeId, int outlineColor, int fillColor, int textColor) {
        return CustomBadges.register(badgeId, new CustomBadge(Components.getComponent(badgeId.withPrefix("custom_badge.")), outlineColor, fillColor, textColor));
    }

    public static void registerModBadge(String modId, Identifier customBadgeId) {
        if (ModBadges.get(modId) == null) ModBadges.register(modId, new ArrayList<>());
        ModBadges.get(modId).add(customBadgeId);
    }

    public static void registerIconOverride(String modId, IconOverride iconOverride) {
        if (IconOverrides.get(modId) == null) IconOverrides.register(modId, new ArrayList<>());
        IconOverrides.get(modId).add(iconOverride);
    }

    public static void registerProfiledDebugEntry(Identifier identifier, DebugScreenEntry debugScreenEntry, DebugScreenProfile profile, DebugScreenEntryStatus status) {
        ProfiledDebugEntries.put(identifier, new ProfiledEntry(profile, status));
        DebugScreenEntries.register(identifier, debugScreenEntry);
    }

    public static void sendToMessageBar(Component message) {
        messageBar.send(message, 40.0F);
    }

    public static void sendToMessageBar(Component message, float ticks) {
        messageBar.send(message, ticks);
    }

    public static void onInitializeClient(AbstractMod mod) {
        onInitialize(SeamClient.getMod(), "Client Events", () -> {
            Badges.onInitializeClient(mod);
            AfterGuiRender.register(mod.idOf("message_overlay"), messageBar::render);
        });
    }

    public static void onTickClient(AbstractMod mod) {
        tryRun(mod, messageBar::tick);
    }
}
