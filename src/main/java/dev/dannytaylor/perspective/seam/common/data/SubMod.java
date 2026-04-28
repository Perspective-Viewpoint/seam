/*
    Seam
    Contributor(s): dannytaylor
    Github: https://github.com/Perspective-Viewpoint/seam
    Licence: GNU LGPLv3
*/

package dev.dannytaylor.perspective.seam.common.data;

import net.fabricmc.loader.api.metadata.ModMetadata;
import net.minecraft.resources.Identifier;
import org.apache.commons.lang3.Strings;

public class SubMod extends FabricMod {
    private final String baseId;

    public SubMod(String id, String baseId, String name, ModMetadata metadata) {
        super(id, name, metadata);
        this.baseId = baseId;
    }

    public static SubMod fromMetadata(String baseId, ModMetadata metadata) {
        return new SubMod(Strings.CS.replaceOnce(metadata.getId(), baseId + "_", ""), baseId, metadata.getName(), metadata);
    }

    public String getBaseId() {
        return this.baseId;
    }

    public String getId() {
        return getId(true);
    }

    public String getId(boolean full) {
        return (!this.baseId.isBlank() && full ? getBaseId() + "_" : "") + this.id;
    }

    public Identifier idOf(String path) {
        return idOf(path, false);
    }

    public Identifier idOf(String path, boolean isBase) {
        return Identifier.fromNamespaceAndPath(isBase ? getBaseId() : getId(true), path);
    }
}
