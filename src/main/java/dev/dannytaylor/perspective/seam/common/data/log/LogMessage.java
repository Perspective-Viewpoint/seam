/*
    Seam
    Contributor(s): dannytaylor
    Github: https://github.com/Perspective-Viewpoint/seam
    Licence: GNU LGPLv3
*/

package dev.dannytaylor.perspective.seam.common.data.log;

import dev.dannytaylor.perspective.seam.common.util.Pair;
import org.apache.commons.lang3.Strings;

public class LogMessage extends Pair<String, Object[]> {
    public LogMessage(String first, Object... second) {
        super(first, second);
    }

    public String get() {
        String message = this.getFirst();
        for (Object arg : this.getSecond()) message = Strings.CS.replaceOnce(message, "{}", String.valueOf(arg));
        return message;
    }
}