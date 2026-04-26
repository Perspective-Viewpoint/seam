package dev.dannytaylor.perspective.seam.common.util;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.Identifier;

public class Components {
    public static MutableComponent getComponent(Identifier identifier) {
        return Component.translatableWithFallback("gui." + identifier.getNamespace() + "." + identifier.getPath(), identifier.getPath().isBlank() ? "" : identifier.toString());
    }
}
