/*
    Seam
    Contributor(s): dannytaylor, Nettakrim
    Github: https://github.com/Perspective-Viewpoint/seam
    Licence: GNU LGPLv3
*/

package dev.dannytaylor.perspective.seam.mixin.client.render;

import com.mojang.blaze3d.resource.CrossFrameResourcePool;
import net.minecraft.client.Camera;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.util.RandomSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(GameRenderer.class)
public interface GameRendererAccessor {
	@Invoker("getFov")
	float seam$getFov(Camera camera, float tickProgress, boolean changingFov);
	@Accessor("resourcePool")
    CrossFrameResourcePool seam$getResourcePool();
	@Accessor("random")
    RandomSource seam$getRandom();
	@Accessor("mainCamera")
    Camera seam$getMainCamera();
}