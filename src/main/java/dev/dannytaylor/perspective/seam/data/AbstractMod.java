/*
    Seam
    Contributor(s): dannytaylor
    Github: https://github.com/Perspective-Viewpoint/seam
    Licence: GNU LGPLv3
*/

package dev.dannytaylor.perspective.seam.data;

import dev.dannytaylor.perspective.seam.data.log.Log;
import net.minecraft.resources.Identifier;

public abstract class AbstractMod {
    protected final String id;
    protected final String name;
    protected final Log logger;

    public AbstractMod(String id, String name) {
        this.id = id;
        this.name = name;
        this.logger = new Log(name);
    }

    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public Log getLogger() {
        return this.logger;
    }

    public Identifier idOf(String path) {
        return Identifier.fromNamespaceAndPath(getId(), path);
    }
}
