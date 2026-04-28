/*
    Seam
    Contributor(s): dannytaylor, Nettakrim
    Github: https://github.com/Perspective-Viewpoint/seam
    Licence: GNU LGPLv3
*/

package dev.dannytaylor.perspective.seam.client.events;

import com.mojang.blaze3d.pipeline.RenderTarget;
import com.mojang.blaze3d.resource.GraphicsResourceAllocator;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

@Environment(EnvType.CLIENT)
public class SeamClientRunnables {
    @FunctionalInterface
    public interface UseItem {
        void run(ItemStack stack, Level level, Player user, InteractionHand hand);
    }

    @FunctionalInterface
    public interface FinishUsingItem {
        void run(ItemStack stack, Level level, LivingEntity user);
    }

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
