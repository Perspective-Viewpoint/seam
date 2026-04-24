/*
    Seam
    Contributor(s): dannytaylor
    Github: https://github.com/Perspective-Viewpoint/seam
    Licence: GNU LGPLv3
*/

package dev.dannytaylor.perspective.seam.client;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.Minecraft;

public class SeamClient implements ClientModInitializer {
	public static Minecraft getMinecraft() {
		return Minecraft.getInstance();
	}

	@Override
	public void onInitializeClient() {
	}
}