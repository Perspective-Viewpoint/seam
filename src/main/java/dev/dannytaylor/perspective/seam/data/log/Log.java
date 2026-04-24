/*
    Seam
    Contributor(s): dannytaylor
    Github: https://github.com/Perspective-Viewpoint/seam
    Licence: GNU LGPLv3
*/

package dev.dannytaylor.perspective.seam.data.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Log {
    private final Logger logger;
    private final String name;

    public Log(String name) {
        this.name = name;
        this.logger = LoggerFactory.getLogger(name);
    }

    private String getName() {
        return this.name;
    }

    private Logger getLogger() {
        return this.logger;
    }

    public void send(LogType type, String message, Object... args) {
        if (type.equals(LogType.INFO)) this.getLogger().info(message, args);
        else if (type.equals(LogType.WARN)) this.getLogger().warn(message, args);
        else if (type.equals(LogType.ERROR)) this.getLogger().error(message, args);
        else if (type.equals(LogType.DEBUG)) this.getLogger().debug(message, args);
    }

    public void info(String message, Object... args) {
        this.send(LogType.INFO, message, args);
    }

    public void warn(String message, Object... args) {
        this.send(LogType.WARN, message, args);
    }

    public void error(String message, Object... args) {
        this.send(LogType.ERROR, message, args);
    }

    public void debug(String message, Object... args) {
        this.send(LogType.DEBUG, message, args);
    }
}