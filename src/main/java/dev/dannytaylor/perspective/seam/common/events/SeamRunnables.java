/*
    Seam
    Contributor(s): dannytaylor
    Github: https://github.com/Perspective-Viewpoint/seam
    Licence: GNU LGPLv3
*/

package dev.dannytaylor.perspective.seam.common.events;

import org.apache.commons.lang3.Strings;

public class SeamRunnables {
    @FunctionalInterface
    public interface NoInputCallable<O> {
        O call();
    }

    @FunctionalInterface
    public interface SingleInputCallable<I, O> {
        O call(I input);
    }

    @FunctionalInterface
    public interface SingleInputRunnable<I> {
        void run(I input);
    }

    public static class Pair<A, B> {
        private final A first;
        private final B second;

        public Pair(A first, B second) {
            this.first = first;
            this.second = second;
        }

        public A getFirst() {
            return this.first;
        }

        public B getSecond() {
            return this.second;
        }
    }

    public static class LogMessage extends Pair<String, Object[]> {
        public LogMessage(String first, Object... second) {
            super(first, second);
        }

        public String get() {
            String message = this.getFirst();
            for (Object arg : this.getSecond()) message = Strings.CS.replaceOnce(message, "{}", String.valueOf(arg));
            return message;
        }
    }
}
