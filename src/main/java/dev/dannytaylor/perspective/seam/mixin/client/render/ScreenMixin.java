/*
    Seam
    Contributor(s): dannytaylor, Nettakrim
    Github: https://github.com/Perspective-Viewpoint/seam
    Licence: GNU LGPLv3
*/

package dev.dannytaylor.perspective.seam.mixin.client.render;

import dev.dannytaylor.perspective.seam.client.SeamClient;
import dev.dannytaylor.perspective.seam.client.events.SeamClientExecute;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(priority = 100, value = Screen.class)
public abstract class ScreenMixin {
    @Inject(method = "renderBackground", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screens/Screen;renderBlurredBackground(Lnet/minecraft/client/gui/GuiGraphics;)V"))
    private void seam$afterBackgroundRender_inWorldBeforeBlur(GuiGraphics guiGraphics, int mouseX, int mouseY, float deltaTicks, CallbackInfo ci) {
        if (SeamClient.getMinecraft().level != null) SeamClientExecute.afterUiBackgroundRender(((GameRendererAccessor) SeamClient.getMinecraft().gameRenderer).seam$getResourcePool());
    }

    @Inject(method = "renderTransparentBackground", at = @At("RETURN"))
    private void seam$afterBackgroundRender_afterInGameBackground(GuiGraphics guiGraphics, CallbackInfo ci) {
        if (SeamClient.getMinecraft().level != null) SeamClientExecute.afterUiBackgroundRender(((GameRendererAccessor) SeamClient.getMinecraft().gameRenderer).seam$getResourcePool());
    }

    @Inject(method = "renderPanorama", at = @At("RETURN"))
    private void seam$afterBackgroundRender_notInWorld(GuiGraphics guiGraphics, float deltaTicks, CallbackInfo ci) {
        if (SeamClient.getMinecraft().level == null) SeamClientExecute.afterUiBackgroundRender(((GameRendererAccessor) SeamClient.getMinecraft().gameRenderer).seam$getResourcePool());
    }
}
