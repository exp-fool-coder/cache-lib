package com.expfool.libs.cachelib.manager;

import java.util.Optional;
import java.util.function.Supplier;

public interface CacheManager<K, V> {

    Optional<V> get(K key);

    Optional<V> get(K key, Supplier<V> supplier);

    void put(K key, V value);

    void remove(K key);
}
