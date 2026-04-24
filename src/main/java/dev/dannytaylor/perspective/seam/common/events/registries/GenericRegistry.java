/*
    Seam
    Contributor(s): Nettakrim
    Github: https://github.com/Perspective-Viewpoint/seam
    Licence: GNU LGPLv3
*/

package dev.dannytaylor.perspective.seam.common.events.registries;

import java.util.HashMap;
import java.util.Map;

public class GenericRegistry<K, V> {
    public final Map<K, V> registry = new HashMap<>();

    public void register(K key, V value) {
        if (!registry.containsKey(key)) registry.put(key, value);
    }

    public V get(K key) {
        return registry.get(key);
    }

    public void modify(K key, V value) {
        registry.replace(key, value);
    }

    public void remove(K key) {
        registry.remove(key);
    }
}
