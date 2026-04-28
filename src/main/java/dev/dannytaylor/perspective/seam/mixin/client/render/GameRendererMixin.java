/*
    Seam
    Contributor(s): dannytaylor
    Github: https://github.com/Perspective-Viewpoint/seam
    Licence: GNU LGPLv3
*/

package dev.dannytaylor.perspective.seam.mixin.client.render;

import com.mojang.blaze3d.resource.CrossFrameResourcePool;
import dev.dannytaylor.perspective.seam.client.events.SeamClientExecute;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.renderer.GameRenderer;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(priority = 100, value = GameRenderer.class)
public abstract class GameRendererMixin {
    @Shadow @Final private CrossFrameResourcePool resourcePool;

    @Inject(method = "render", at = @At("HEAD"))
    private void seam$beforeGameRender(DeltaTracker tickCounter, boolean tick, CallbackInfo ci) {
        SeamClientExecute.beforeGameRender();
    }

    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/fog/FogRenderer;endFrame()V"))
    private void seam$afterPostRender(DeltaTracker tickCounter, boolean tick, CallbackInfo ci) {
        SeamClientExecute.afterVanillaPostEffectRender(this.resourcePool);
    }

    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screens/Screen;renderWithTooltipAndSubtitles(Lnet/minecraft/client/gui/GuiGraphics;IIF)V"))
    private void seam$beforeUiRender(DeltaTracker tickCounter, boolean tick, CallbackInfo ci) {
        SeamClientExecute.beforeUiRender(this.resourcePool);
    }

    @Inject(method = "render", at = @At("TAIL"))
    private void seam$afterUiRender(DeltaTracker tickCounter, boolean tick, CallbackInfo ci) {
        SeamClientExecute.afterUiRender(this.resourcePool);
    }


    @Inject(method = "resize", at = @At(value = "TAIL"))
    private void seam$onResized(int width, int height, CallbackInfo ci) {
        SeamClientExecute.resize(width, height);
    }
}
