/*
    Seam
    Contributor(s): Nettakrim, dannytaylor
    Github: https://github.com/Perspective-Viewpoint/seam
    Licence: GNU LGPLv3
*/

package dev.dannytaylor.perspective.seam.common.events.registries;

import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;

public class GenericRegistry<K, V> {
    public final Map<K, V> registry;

    public GenericRegistry(Map<K, V> registry) {
        this.registry = registry;
    }

    /**
     * Registers the specified value to the specified key, if the specified key is not already assigned.
     * @param key key with which the specified value is to be associated.
     * @param value value to be associated with the specified key.
     * @return the specified key, or
     *         {@code null} if this registry already contained the specified key.
    */
    public K register(K key, V value) {
        if (!registry.containsKey(key)) {
            this.put(key, value);
            return key;
        }
        return null;
    }

    /**
     * @param key key with which the specified value is to be associated.
     * @param value value to be associated with the specified key.
     * @return the previous value associated with the specified key, or
     *         {@code null} if there was no mapping for the key.
     */
    public V put(K key, V value) {
        return registry.put(key, value);
    }

    /**
     * @return value associated with the specified key, or
     *         {@code null} if there is no mapping for the key.
    */
    public V get(K key) {
        return registry.get(key);
    }

    /**
     * Replaces the entry for the specified key only if it is currently mapped to some value (optional operation).
     * @param key key with which the specified value is to be associated.
     * @param value value to be associated with the specified key.
     * @return the previous value associated with the specified key, or
     *         {@code null} if there was no mapping for the key.
    */
    public V modify(K key, V value) {
        return registry.replace(key, value);
    }

    /**
     * Removes the mapping for a key from this map if it is present (optional operation).
     * @param key key with which the specified value is to be associated.
     * @return the previous value associated with the specified key, or
     *         {@code null} if there was no mapping for the key.
     */
    public V remove(K key) {
        return registry.remove(key);
    }

    /**
     * Performs the given action for each entry in this registry until all entries have been processed or
     *         the action throws an exception.
     * @param action The action to be performed for each entry.
     */
    public void forEach(BiConsumer<? super K, ? super V> action) {
        registry.forEach(action);
    }

    /**
     * @return a {@link Set} view of the keys contained in this registry.
    */
    public Set<K> keySet() {
        return registry.keySet();
    }
}
