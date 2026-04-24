/*
    Seam
    Contributor(s): dannytaylor
    Github: https://github.com/Perspective-Viewpoint/seam
    Licence: GNU LGPLv3
*/

package dev.dannytaylor.perspective.seam.common.data.log;

import dev.dannytaylor.perspective.seam.common.data.AbstractMod;

public class SeamLog {
    public static void send(AbstractMod mod, LogType type, String message, Object... args) {
        mod.getLogger().send(type, message, args);
    }

    public static void info(AbstractMod mod, String message, Object... args) {
        mod.getLogger().info(message, args);
    }

    public static void warn(AbstractMod mod, String message, Object... args) {
        mod.getLogger().warn(message, args);
    }

    public static void error(AbstractMod mod, String message, Object... args) {
        mod.getLogger().error(message, args);
    }

    public static void debug(AbstractMod mod, String message, Object... args) {
        mod.getLogger().debug(message, args);
    }
}