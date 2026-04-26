/*
    Seam
    Contributor(s): dannytaylor
    Github: https://github.com/Perspective-Viewpoint/seam
    Licence: GNU LGPLv3
*/

package dev.dannytaylor.perspective.seam.client.events;

import dev.dannytaylor.perspective.seam.client.SeamClient;
import dev.dannytaylor.perspective.seam.common.data.AbstractMod;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.resources.Identifier;

@Environment(EnvType.CLIENT)
public class Badges {
    public static final Identifier PERSPECTIVE = SeamClientEvents.registerCustomBadge(SeamClient.getMod().idOf("perspective"),
            0xFF053654,
            0xFF26AEFF,
            0xFFFFFFFF
    );
    public static final Identifier LUMINANCE = SeamClientEvents.registerCustomBadge(
            SeamClient.getMod().idOf("luminance"),
            0xFFFF8F8F,
            0xFFB73A3A,
            0xFFFFFFFF
    );

    /** This function is used to register seam's custom badges, it shouldn't be used by other projects. */
    public static void onInitializeClient(AbstractMod mod) {
        SeamClientEvents.onInitialize(mod, "Custom Badges", () -> {});
    }

    public static void perspective(String modId) {
        SeamClientEvents.registerModBadge(modId, PERSPECTIVE);
    }

    public static void luminance(String modId) {
        SeamClientEvents.registerModBadge(modId, LUMINANCE);
    }
}
