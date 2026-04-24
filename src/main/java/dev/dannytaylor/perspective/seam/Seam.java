/*
    Seam
    Contributor(s): dannytaylor
    Github: https://github.com/Perspective-Viewpoint/seam
    Licence: GNU LGPLv3
*/

package dev.dannytaylor.perspective.seam;

import dev.dannytaylor.perspective.seam.data.PerspectiveMod;
import dev.dannytaylor.perspective.seam.events.SeamEvents;
import net.fabricmc.api.ModInitializer;

public class Seam implements ModInitializer {
	private static final PerspectiveMod mod = new PerspectiveMod("seam", "Seam");

	public static PerspectiveMod getMod() {
		return mod;
	}

	@Override
	public void onInitialize() {
		SeamEvents.onInitialize(getMod(), getMod().getName(), () -> {

		}, true);
	}
}