/*
    Seam
    Contributor(s): dannytaylor
    Github: https://github.com/Perspective-Viewpoint/seam
    Licence: GNU LGPLv3
*/

package dev.dannytaylor.perspective.seam.client;

import dev.dannytaylor.perspective.seam.client.events.SeamClientEvents;
import dev.dannytaylor.perspective.seam.common.Seam;
import dev.dannytaylor.perspective.seam.common.data.PerspectiveMod;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.Minecraft;

@Environment(EnvType.CLIENT)
public class SeamClient implements ClientModInitializer {
	public static Minecraft getMinecraft() {
		return Minecraft.getInstance();
	}

	public static PerspectiveMod getMod() {
		return Seam.getMod();
	}

	@Override
	public void onInitializeClient() {
		SeamClientEvents.onInitialize(getMod(), "Client", () -> {
			SeamClientEvents.onInitializeClient(getMod());
			ClientTickEvents.START_CLIENT_TICK.register((client) -> {
				SeamClientEvents.onTickClient(getMod());
			});
		}, true);
	}
}