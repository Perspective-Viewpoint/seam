/*
    Seam
    Contributor(s): dannytaylor
    Github: https://github.com/Perspective-Viewpoint/seam
    Licence: GNU LGPLv3
*/

package dev.dannytaylor.perspective.seam.client.events;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
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
}
