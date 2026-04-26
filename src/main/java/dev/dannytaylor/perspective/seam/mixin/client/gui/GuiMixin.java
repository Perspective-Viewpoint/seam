/*
    Seam
    Contributor(s): dannytaylor
    Github: https://github.com/Perspective-Viewpoint/seam
    Licence: GNU LGPLv3
*/

package dev.dannytaylor.perspective.seam.mixin.client.gui;

import dev.dannytaylor.perspective.seam.client.events.SeamClientExecute;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(priority = 100, value = Gui.class)
public abstract class GuiMixin {
	@Shadow @Final private Minecraft minecraft;

	@Inject(at = @At(value = "HEAD"), method = "render")
	private void luminance$renderBefore(GuiGraphics guiGraphics, DeltaTracker tickCounter, CallbackInfo ci) {
		if (!this.minecraft.gameRenderer.isPanoramicMode()) {
			SeamClientExecute.beforeGuiRender(guiGraphics, tickCounter);
		}
	}

	@Inject(at = @At(value = "TAIL"), method = "render")
	private void luminance$renderAfter(GuiGraphics guiGraphics, DeltaTracker tickCounter, CallbackInfo ci) {
		if (!this.minecraft.gameRenderer.isPanoramicMode()) {
			SeamClientExecute.afterGuiRender(guiGraphics, tickCounter);
		}
	}
}