/*
    Seam
    Contributor(s): dannytaylor
    Github: https://github.com/Perspective-Viewpoint/seam
    Licence: GNU LGPLv3
*/

package dev.dannytaylor.perspective.seam.common.events;

import dev.dannytaylor.perspective.seam.common.data.AbstractMod;
import dev.dannytaylor.perspective.seam.common.data.log.SeamLog;

public class SeamEvents {
    public static void onInitialize(AbstractMod mod, String name, Runnable onInitialize) {
        onInitialize(mod, name, onInitialize, false);
    }

    public static void onInitialize(AbstractMod mod, String name, Runnable onInitialize, boolean logFinish) {
        String initializingName = getInitializingName(name);
        SeamLog.info(mod, "Initializing{}...", initializingName);
        try {
            onInitialize.run();
            if (logFinish) SeamLog.info(mod, "Finished initializing{}!", initializingName);
        } catch (Exception error) {
            SeamLog.error(mod, "Failed to initialize{}", initializingName, error);
        }
    }

    private static String getInitializingName(String name) {
        return name.isBlank() ? name : " " + name;
    }
}
