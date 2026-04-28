/*
    Seam
    Contributor(s): dannytaylor
    Github: https://github.com/Perspective-Viewpoint/seam
    Licence: GNU LGPLv3
*/

package dev.dannytaylor.perspective.seam.common.events;

import dev.dannytaylor.perspective.seam.common.data.AbstractMod;
import dev.dannytaylor.perspective.seam.common.data.log.LogMessage;
import dev.dannytaylor.perspective.seam.common.data.log.SeamLog;

public class SeamEvents {
    public static void onInitialize(AbstractMod mod, String name, Runnable onInitialize) {
        onInitialize(mod, name, onInitialize, false);
    }

    public static void onInitialize(AbstractMod mod, String name, Runnable onInitialize, boolean logFinish) {
        String initializingName = getInitializingName(name);
        SeamLog.info(mod, "Initializing{}...", initializingName);
        tryRun(mod, () -> {
            onInitialize.run();
            if (logFinish) SeamLog.info(mod, "Finished initializing{}!", initializingName);
        }, (error) -> new LogMessage("Failed to initialize{}!", initializingName));
    }

    public static void tryRun(AbstractMod mod, Runnable runnable) {
        tryRun(mod, runnable, (error) -> getErrorMessage());
    }

    public static void tryRun(AbstractMod mod, Runnable runnable, SeamCallables.SingleInputCallable<Exception, LogMessage> errorMessage) {
        try {
            runnable.run();
        } catch (Exception error) {
            LogMessage logMessage = errorMessage.call(error);
            SeamLog.error(mod, logMessage.get(), error);
        }
    }

    public static LogMessage getErrorMessage() {
        return new LogMessage("Caught an exception!");
    }

    private static String getInitializingName(String name) {
        return name.isBlank() ? name : " " + name;
    }
}
