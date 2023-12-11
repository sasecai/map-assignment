package model.containers;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface MyIDictionary<K, V> {
    void insert(K key, V value);
    V access(K key);

    boolean isDefined(K key);
    String toStr();
    Set<K> getKeys();
    Collection<V> getValues();

    void remove(K strVal);
}
