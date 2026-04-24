/*
    Seam
    Contributor(s): dannytaylor
    Github: https://github.com/Perspective-Viewpoint/seam
    Licence: GNU LGPLv3
*/

package dev.dannytaylor.perspective.seam.common.data;

import net.minecraft.resources.Identifier;

public class SubMod extends AbstractMod {
    private final String baseId;

    public SubMod(String id, String baseId, String name) {
        super(id, name);
        this.baseId = baseId;
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
