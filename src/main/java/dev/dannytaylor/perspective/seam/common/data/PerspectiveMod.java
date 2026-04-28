/*
    Seam
    Contributor(s): dannytaylor
    Github: https://github.com/Perspective-Viewpoint/seam
    Licence: GNU LGPLv3
*/

package dev.dannytaylor.perspective.seam.common.data;

import net.fabricmc.loader.api.metadata.ModMetadata;
import org.apache.commons.lang3.Strings;

public class PerspectiveMod extends SubMod {
    public PerspectiveMod(String id, String name, ModMetadata metadata) {
        super(id, getPerspective(), name, metadata);
    }

    public static PerspectiveMod fromMetadata(ModMetadata metadata) {
        return new PerspectiveMod(Strings.CS.replaceOnce(metadata.getId(), getPerspective() + "_", ""), metadata.getName(), metadata);
    }

    public static String getPerspective() {
        return "perspective";
    }
}
