/*
    Seam
    Contributor(s): dannytaylor
    Github: https://github.com/Perspective-Viewpoint/seam
    Licence: GNU LGPLv3
*/

package dev.dannytaylor.perspective.seam.events.registries;

import dev.dannytaylor.perspective.seam.events.registries.entries.PriorityEntry;
import net.minecraft.resources.Identifier;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class PriorityRegistry<V> extends Registry<PriorityEntry<V>> {
    public Map<Identifier, V> getRegistry() {
        return this.getSorted().entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, entry -> entry.getValue().entry()));
    }

    public Map<Identifier, PriorityEntry<V>> getSorted() {
        return this.registry.entrySet().stream().sorted(
                Map.Entry.<Identifier, PriorityEntry<V>>comparingByValue(
                        Comparator.comparingDouble(PriorityEntry::priority)
                ).thenComparing(Map.Entry.comparingByKey())
        ).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (a, b) -> a, LinkedHashMap::new));
    }

    public void register(Identifier key, V value) {
        register(key, value, Float.MAX_VALUE - 1.0F);
    }

    public void register(Identifier key, V value, float priority) {
        if (!this.registry.containsKey(key)) {
            this.registry.put(key, new PriorityEntry<>(value, priority));
        }
    }
}
