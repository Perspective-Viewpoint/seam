/*
    Seam
    Contributor(s): dannytaylor
    Github: https://github.com/Perspective-Viewpoint/seam
    Licence: GNU LGPLv3
*/

package dev.dannytaylor.perspective.seam.client.events;

import dev.dannytaylor.perspective.seam.common.data.AbstractMod;
import dev.dannytaylor.perspective.seam.common.events.SeamEvents;
import dev.dannytaylor.perspective.seam.common.events.registries.GenericRegistry;
import dev.dannytaylor.perspective.seam.common.events.registries.Registry;
import dev.dannytaylor.perspective.seam.common.util.Components;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Environment(EnvType.CLIENT)
public class SeamClientEvents extends SeamEvents {
    public static final Registry<CustomBadge> CustomBadges = new Registry<>();
    public static final GenericRegistry<String, List<Identifier>> ModBadges = new GenericRegistry<>();
    public static final GenericRegistry<String, List<IconOverride>> IconOverrides = new GenericRegistry<>();

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

    public static Optional<IconOverride> getIconOverride(String modId) {
        List<IconOverride> iconOverrides = IconOverrides.get(modId);
        if (iconOverrides != null) {
            for (IconOverride iconOverride : iconOverrides.stream().sorted(Comparator.comparing((iconOverride) -> iconOverride.getIconId().toString(), String.CASE_INSENSITIVE_ORDER)).toList()) {
                if (iconOverride.shouldOverride()) return Optional.of(iconOverride);
            }
        }
        return Optional.empty();
    }

    public static void sendToMessageBar(Component message) {
        messageBar.send(message, 40.0F);
    }

    public static void sendToMessageBar(Component message, float ticks) {
        messageBar.send(message, ticks);
    }

    public static void onTickClient(AbstractMod mod) {
        tryRun(mod, messageBar::tick);
    }
}
