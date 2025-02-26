package ru.promo.teamspace.cachelib.adapter;

import java.time.Duration;

public interface TtlCacheAdapter<K, V> extends CacheAdapter<K, V> {

    void put(K key, V value, Duration ttl);
}
