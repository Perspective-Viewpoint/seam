/*
    Seam
    Contributor(s): dannytaylor
    Github: https://github.com/Perspective-Viewpoint/seam
    Licence: GNU LGPLv3
*/

package dev.dannytaylor.perspective.seam.common;

import dev.dannytaylor.perspective.seam.common.data.PerspectiveMod;
import net.fabricmc.api.ModInitializer;

public class Seam implements ModInitializer {
	private static final PerspectiveMod mod = new PerspectiveMod("seam", "Seam");

	public static PerspectiveMod getMod() {
		return mod;
	}

	@Override
	public void onInitialize() {
	}
}