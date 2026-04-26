/*
    Seam
    Contributor(s): dannytaylor
    Github: https://github.com/Perspective-Viewpoint/seam
    Licence: GNU LGPLv3
*/

package dev.dannytaylor.perspective.seam.client.events;

import dev.dannytaylor.perspective.seam.common.events.SeamRunnables;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.resources.Identifier;

@Environment(EnvType.CLIENT)
public class IconOverride {
    private Identifier iconId;
    private SeamRunnables.NoInputCallable<Boolean> shouldOverride;

    public IconOverride(Identifier iconId, SeamRunnables.NoInputCallable<Boolean> shouldOverride) {
        this.iconId = iconId;
        this.shouldOverride = shouldOverride;
    }

    public Identifier getIconId() {
        return this.iconId;
    }

    public void setIconId(Identifier iconId) {
        this.iconId = iconId;
    }

    public String getIconLocation() {
        return "assets/" + getIconId().getNamespace() + "/" + getIconId().getPath();
    }

    public boolean shouldOverride() {
        return this.shouldOverride.call();
    }

    public void setShouldOverride(SeamRunnables.NoInputCallable<Boolean> shouldOverride) {
        this.shouldOverride = shouldOverride;
    }
}
