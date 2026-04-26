/*
    Seam
    Contributor(s): dannytaylor
    Github: https://github.com/Perspective-Viewpoint/seam
    Licence: GNU LGPLv3
*/

package dev.dannytaylor.perspective.seam.client.events;

import dev.dannytaylor.perspective.seam.client.SeamClient;
import dev.dannytaylor.perspective.seam.common.events.SeamExecute;
import dev.dannytaylor.perspective.seam.common.events.SeamRunnables;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.debug.DebugScreenEntryStatus;
import net.minecraft.client.gui.components.debug.DebugScreenProfile;
import net.minecraft.resources.Identifier;

import java.util.*;

public class SeamClientExecute extends SeamExecute {
    public static void beforeGuiRender(GuiGraphics guiGraphics, DeltaTracker renderTickCounter) {
        SeamClientEvents.BeforeGuiRender.registry.forEach(((id, runnable) -> {
            SeamClientEvents.tryRun(SeamClient.getMod(), () -> runnable.run(guiGraphics, renderTickCounter), input -> new SeamRunnables.LogMessage("Failed to execute BeforeGuiRender event with id: {}", id));
        }));
    }

    public static void afterGuiRender(GuiGraphics guiGraphics, DeltaTracker renderTickCounter) {
        SeamClientEvents.AfterGuiRender.registry.forEach(((id, runnable) -> SeamClientEvents.tryRun(SeamClient.getMod(), () -> {
            runnable.run(guiGraphics, renderTickCounter);
        }, input -> new SeamRunnables.LogMessage("Failed to execute AfterGuiRender event with id: {}", id))));
    }

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
}
