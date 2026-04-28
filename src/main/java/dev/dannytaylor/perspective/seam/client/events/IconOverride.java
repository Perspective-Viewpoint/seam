/*
    Seam
    Contributor(s): dannytaylor
    Github: https://github.com/Perspective-Viewpoint/seam
    Licence: GNU LGPLv3
*/

package dev.dannytaylor.perspective.seam.client.events;

import dev.dannytaylor.perspective.seam.client.SeamClient;
import dev.dannytaylor.perspective.seam.common.data.log.SeamLog;
import dev.dannytaylor.perspective.seam.common.events.SeamEvents;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.resources.Identifier;

import java.util.concurrent.Callable;

@Environment(EnvType.CLIENT)
public class IconOverride {
    private Identifier iconId;
    private Callable<Boolean> shouldOverride;

    public IconOverride(Identifier iconId, Callable<Boolean> shouldOverride) {
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
        try {
            return this.shouldOverride.call();
        } catch (Exception error) {
            SeamLog.error(SeamClient.getMod(), SeamEvents.getErrorMessage().get(), error);
        }
        return false;
    }

    public void setShouldOverride(Callable<Boolean> shouldOverride) {
        this.shouldOverride = shouldOverride;
    }
}
