/*
    Seam
    Contributor(s): Nettakrim, dannytaylor
    Github: https://github.com/Perspective-Viewpoint/seam
    Licence: GNU LGPLv3
*/

package dev.dannytaylor.perspective.seam.common.events.registries;

import java.util.HashMap;
import java.util.Map;

public class GenericRegistry<K, V> {
    public final Map<K, V> registry = new HashMap<>();

    /**
     * @param key key with which the specified value is to be associated
     * @param value value to be associated with the specified key
     * @return the specified key, or `null` if this registry already contains the specified key
    */
    public K register(K key, V value) {
        if (!registry.containsKey(key)) {
            registry.put(key, value);
            return key;
        }
        return null;
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
