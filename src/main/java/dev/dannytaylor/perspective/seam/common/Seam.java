/*
    Seam
    Contributor(s): dannytaylor
    Github: https://github.com/Perspective-Viewpoint/seam
    Licence: GNU LGPLv3
*/

package dev.dannytaylor.perspective.seam.common;

import dev.dannytaylor.perspective.seam.common.data.PerspectiveMod;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;

public class Seam implements ModInitializer {
	private static final PerspectiveMod mod = PerspectiveMod.fromMetadata(FabricLoader.getInstance().getModContainer("perspective_seam").orElseThrow().getMetadata());

	public static PerspectiveMod getMod() {
		return mod;
	}

	@Override
	public void onInitialize() {
	}
}