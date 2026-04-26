/*
    Seam
    Contributor(s): dannytaylor
    Github: https://github.com/Perspective-Viewpoint/seam
    Licence: GNU LGPLv3
*/

package dev.dannytaylor.perspective.seam.client.events;

import dev.dannytaylor.perspective.seam.client.SeamClient;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;

@Environment(EnvType.CLIENT)
public class MessageBar {
    private Component component = Component.empty();
    private float ticksRemaining;

    public void tick() {
        if (ticksRemaining > 0) ticksRemaining -= 1;
        else if (!component.equals(Component.empty())) {
            component = Component.empty();
        }
    }

    public void send(Component message) {
        this.send(message, 40.0F);
    }

    public void send(Component message, float ticks) {
        this.component = message;
        this.setRemainingTicks(ticks);
    }

    private void setRemainingTicks(float ticks) {
        this.ticksRemaining = ticks;
    }

    public void render(GuiGraphics guiGraphics, DeltaTracker deltaTracker) {
        int time = (int) Math.min((this.ticksRemaining - deltaTracker.getGameTimeDeltaPartialTick(true)) * 255.0F / 20.0F, 255.0F);
        if (time > 10) guiGraphics.drawCenteredString(SeamClient.getMinecraft().font, this.component, (int) (SeamClient.getMinecraft().getWindow().getGuiScaledWidth() / 2.0F), 23, 16777215 | (time << 24 & -16777216));
    }
}
