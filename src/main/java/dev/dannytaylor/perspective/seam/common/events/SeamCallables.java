/*
    Seam
    Contributor(s): dannytaylor
    Github: https://github.com/Perspective-Viewpoint/seam
    Licence: GNU LGPLv3
*/

package dev.dannytaylor.perspective.seam.common.events;

public class SeamCallables {
    @FunctionalInterface
    public interface NoInputCallable<O> {
        O call();
    }

    @FunctionalInterface
    public interface SingleInputCallable<I, O> {
        O call(I input);
    }
}
