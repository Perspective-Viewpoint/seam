package dev.dannytaylor.perspective.seam.mixin.client.resources;

import dev.dannytaylor.perspective.seam.client.events.SeamClientExecute;
import net.minecraft.client.Minecraft;
import net.minecraft.client.main.GameConfig;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.server.packs.resources.ReloadableResourceManager;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(priority = 100, value = Minecraft.class)
public abstract class MinecraftMixin {
    @Shadow @Final private ReloadableResourceManager resourceManager;

    @Inject(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/resources/language/LanguageManager;<init>(Ljava/lang/String;Ljava/util/function/Consumer;)V"))
    private void seam$clientInit(GameConfig runArgs, CallbackInfo ci) {
        SeamClientExecute.registerClientResourceReloaders(resourceManager);
    }

    @Inject(method = "onResourceLoadFinished", at = @At("HEAD"))
    private void seam$finishedLoading(CallbackInfo ci) {
        SeamClientExecute.afterClientResourceReload();
    }

    @Inject(at = @At("TAIL"), method = "setLevel")
    void seam$onJoinWorld(ClientLevel world, CallbackInfo ci) {
        SeamClientExecute.onJoinWorld();
    }

    @Inject(at = @At("HEAD"), method = "disconnect(Lnet/minecraft/client/gui/screens/Screen;ZZ)V")
    void seam$onLeaveWorld(CallbackInfo ci) {
        SeamClientExecute.onLeaveWorld();
    }
}
