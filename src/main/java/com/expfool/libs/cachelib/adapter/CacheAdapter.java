package com.expfool.libs.cachelib.adapter;

public interface CacheAdapter<K, V> extends ReadonlyCacheAdapter<K, V> {

    void put(K key, V value);

    void remove(K key);
}
