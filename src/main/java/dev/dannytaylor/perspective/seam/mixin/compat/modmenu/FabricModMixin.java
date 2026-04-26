/*
    Seam
    Contributor(s): dannytaylor
    Github: https://github.com/Perspective-Viewpoint/seam
    Licence: GNU LGPLv3
*/

package dev.dannytaylor.perspective.seam.mixin.compat.modmenu;

import com.terraformersmc.modmenu.util.mod.fabric.FabricIconHandler;
import com.terraformersmc.modmenu.util.mod.fabric.FabricMod;
import dev.dannytaylor.perspective.seam.client.events.SeamClientExecute;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.fabricmc.loader.api.metadata.ModMetadata;
import net.minecraft.client.renderer.texture.DynamicTexture;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;

@Environment(EnvType.CLIENT)
@Mixin(priority = 100, value = FabricMod.class, remap = false)
public abstract class FabricModMixin {
	@Shadow @Final protected ModMetadata metadata;

	@Inject(method = "getIcon", at = @At("RETURN"), cancellable = true)
	private void luminance$getIcon(FabricIconHandler iconHandler, int size, CallbackInfoReturnable<DynamicTexture> cir) {
		SeamClientExecute.getIconOverride(metadata.getId()).ifPresent(iconOverride -> {
			String iconPath = iconOverride.getIconLocation();
			Optional<ModContainer> modContainer = FabricLoader.getInstance().getModContainer(this.metadata.getId());
			if (modContainer.isPresent() && iconPath != null) cir.setReturnValue(iconHandler.createIcon(modContainer.get(), iconPath));
		});
	}
}