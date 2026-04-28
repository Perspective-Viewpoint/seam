/*
    Seam
    Contributor(s): dannytaylor
    Github: https://github.com/Perspective-Viewpoint/seam
    Licence: GNU LGPLv3
*/

package dev.dannytaylor.perspective.seam.mixin.client.render;

import dev.dannytaylor.perspective.seam.client.events.SeamClientExecute;
import net.minecraft.client.gui.components.debug.DebugScreenEntryList;
import net.minecraft.client.gui.components.debug.DebugScreenEntryStatus;
import net.minecraft.client.gui.components.debug.DebugScreenProfile;
import net.minecraft.resources.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;

@Mixin(DebugScreenEntryList.class)
public abstract class DebugScreenEntryListMixin {
    @Shadow private Map<Identifier, DebugScreenEntryStatus> allStatuses;

    @Inject(method = "loadDefaultProfile", at = @At("RETURN"))
    private void luminance$loadDefaultProfile(CallbackInfo ci) {
        this.allStatuses.putAll(SeamClientExecute.getProfiledDebugEntries(DebugScreenProfile.DEFAULT));
    }

    @Inject(method = "loadProfile", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/components/debug/DebugScreenEntryList;rebuildCurrentList()V"))
    private void luminance$loadProfile(DebugScreenProfile debugScreenProfile, CallbackInfo ci) {
        this.allStatuses.putAll(SeamClientExecute.getProfiledDebugEntries(debugScreenProfile));
    }
}
