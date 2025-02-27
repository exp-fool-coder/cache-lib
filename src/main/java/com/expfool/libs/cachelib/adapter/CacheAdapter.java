package com.expfool.libs.cachelib.adapter;

import java.util.Optional;

public interface CacheAdapter<K, V> {

    Optional<V> get(K key);

    void put(K key, V value);

    void remove(K key);
}
