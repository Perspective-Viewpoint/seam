/*
    Seam
    Contributor(s): dannytaylor, Nettakrim
    Github: https://github.com/Perspective-Viewpoint/seam
    Licence: GNU LGPLv3
*/

package dev.dannytaylor.perspective.seam.client.events;

import com.mojang.blaze3d.pipeline.RenderTarget;
import com.mojang.blaze3d.resource.GraphicsResourceAllocator;
import dev.dannytaylor.perspective.seam.common.events.SeamRunnables;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.gui.GuiGraphics;

public class SeamClientRunnables extends SeamRunnables {
    @FunctionalInterface
    public interface GuiRender {
        void run(GuiGraphics guiGraphics, DeltaTracker deltaTracker);
    }

    @FunctionalInterface
    public interface OnResized {
        void run(int width, int height);
    }

    @FunctionalInterface
    public interface GameRender {
        void run(RenderData data);
    }

    @FunctionalInterface
    public interface PanoramaRender {
        void run(GuiGraphics guiGraphics, int width, int height, boolean rotate, RenderData data);
    }

    public record RenderData(RenderTarget renderTarget, GraphicsResourceAllocator resourceAllocator) {}
}
