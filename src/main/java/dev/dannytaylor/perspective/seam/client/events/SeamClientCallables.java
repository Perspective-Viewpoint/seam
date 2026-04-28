/*
    Seam
    Contributor(s): dannytaylor, Nettakrim
    Github: https://github.com/Perspective-Viewpoint/seam
    Licence: GNU LGPLv3
*/

package dev.dannytaylor.perspective.seam.client.events;

import dev.dannytaylor.perspective.seam.common.events.SeamCallables;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.input.MouseButtonInfo;
import org.joml.Vector2i;

@Environment(EnvType.CLIENT)
public class SeamClientCallables extends SeamCallables {
    @FunctionalInterface
    public interface OnMouseScroll {
        boolean call(long windowHandle, double horizontal, double vertical, Vector2i scroll);
    }

    @FunctionalInterface
    public interface OnMouseButton {
        boolean call(long windowHandle, MouseButtonInfo mouseButtonInfo, @MouseButtonInfo.Action int action);
    }
}
