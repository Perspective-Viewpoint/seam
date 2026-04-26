/*
    Seam
    Contributor(s): dannytaylor
    Github: https://github.com/Perspective-Viewpoint/seam
    Licence: GNU LGPLv3
*/

package dev.dannytaylor.perspective.seam.common.events.registries;

import java.util.HashMap;

public class HashRegistry<K, V> extends GenericRegistry<K, V> {
    public HashRegistry() {
        super(new HashMap<>());
    }
}
