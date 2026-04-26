/*
    Seam
    Contributor(s): dannytaylor
    Github: https://github.com/Perspective-Viewpoint/seam
    Licence: GNU LGPLv3
*/

package dev.dannytaylor.perspective.seam.mixin.compat.modmenu;

import com.terraformersmc.modmenu.util.DrawingUtil;
import com.terraformersmc.modmenu.util.mod.Mod;
import com.terraformersmc.modmenu.util.mod.ModBadgeRenderer;
import dev.dannytaylor.perspective.seam.client.SeamClient;
import dev.dannytaylor.perspective.seam.client.events.CustomBadge;
import dev.dannytaylor.perspective.seam.client.events.SeamClientEvents;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Environment(EnvType.CLIENT)
@Mixin(priority = 100, value = ModBadgeRenderer.class, remap = false)
public abstract class ModsScreenMixin {
	@Shadow protected int badgeX;
	@Shadow protected int badgeMax;
	@Shadow protected int badgeY;
	@Shadow protected Mod mod;
	@Inject(method = "draw", at = @At(value = "INVOKE", target = "Ljava/util/Set;forEach(Ljava/util/function/Consumer;)V"))
	private void luminance$draw(GuiGraphics guiGraphics, int mouseX, int mouseY, CallbackInfo ci) {
		List<Identifier> badges = SeamClientEvents.ModBadges.get(this.mod.getId());
		if (badges != null) {
			for (Identifier badgeId : badges) {
				CustomBadge customBadge = SeamClientEvents.CustomBadges.get(badgeId);
				if (customBadge != null) {
					int width = SeamClient.getMinecraft().font.width(customBadge.text()) + 6;
					if (badgeX + width < badgeMax) {
						DrawingUtil.drawBadge(guiGraphics, badgeX, badgeY, width, customBadge.text().getVisualOrderText(), customBadge.outlineColor(), customBadge.fillColor(), customBadge.textColor());
						badgeX += width + 3;
					}
				}
			}
		}
	}
}
