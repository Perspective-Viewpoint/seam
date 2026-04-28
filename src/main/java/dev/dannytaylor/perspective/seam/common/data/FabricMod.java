/*
    Seam
    Contributor(s): dannytaylor
    Github: https://github.com/Perspective-Viewpoint/seam
    Licence: GNU LGPLv3
*/

package dev.dannytaylor.perspective.seam.common.data;

import net.fabricmc.loader.api.metadata.ModMetadata;

public class FabricMod extends AbstractMod {
    private final ModMetadata metadata;

    public FabricMod(String id, String name, ModMetadata metadata) {
        super(id, name);
        this.metadata = metadata;
    }

    public static FabricMod fromMetadata(ModMetadata metadata) {
        return new FabricMod(metadata.getId(), metadata.getName(), metadata);
    }

    public ModMetadata getMetadata() {
        return metadata;
    }
}
