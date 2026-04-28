/*
    Seam
    Contributor(s): dannytaylor
    Github: https://github.com/Perspective-Viewpoint/seam
    Licence: GNU LGPLv3
*/

package dev.dannytaylor.perspective.seam.mixin.client.mouse;

import com.llamalad7.mixinextras.sugar.Local;
import dev.dannytaylor.perspective.seam.client.events.SeamClientExecute;
import net.minecraft.client.MouseHandler;
import net.minecraft.client.input.MouseButtonInfo;
import org.joml.Vector2i;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(priority = 100, value = MouseHandler.class)
public abstract class MouseHandlerMixin {
	@Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/client/player/LocalPlayer;isSpectator()Z"), method = "onScroll", cancellable = true)
	private void perspective$onScroll(long windowHandle, double horizontal, double vertical, CallbackInfo ci, @Local Vector2i scroll) {
		if (SeamClientExecute.onMouseScroll(windowHandle, horizontal, vertical, scroll)) ci.cancel();
	}

	@Inject(at = @At("HEAD"), method = "onButton", cancellable = true)
	private void perspective$onButton(long windowHandle, MouseButtonInfo mouseButtonInfo, int i, CallbackInfo ci) {
		if (SeamClientExecute.onMouseButton(windowHandle, mouseButtonInfo, i)) ci.cancel();
	}
}