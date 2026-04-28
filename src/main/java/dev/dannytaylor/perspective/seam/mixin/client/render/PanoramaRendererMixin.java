/*
    Seam
    Contributor(s): dannytaylor
    Github: https://github.com/Perspective-Viewpoint/seam
    Licence: GNU LGPLv3
*/

package dev.dannytaylor.perspective.seam.mixin.client.render;

import dev.dannytaylor.perspective.seam.client.SeamClient;
import dev.dannytaylor.perspective.seam.client.events.SeamClientExecute;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.PanoramaRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(priority = 100, value = PanoramaRenderer.class)
public class PanoramaRendererMixin {
	@Inject(method = "render", at = @At("RETURN"))
	private void seam$afterPanoramaRender(GuiGraphics guiGraphics, int width, int height, boolean rotate, CallbackInfo ci) {
		SeamClientExecute.afterPanoramaRender(guiGraphics, width, height, rotate, ((GameRendererAccessor) SeamClient.getMinecraft().gameRenderer).seam$getResourcePool());
	}
}
