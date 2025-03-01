package com.expfool.libs.cachelib.manager;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.expfool.libs.cachelib.adapter.TtlCacheAdapter;
import com.expfool.libs.cachelib.manager.key.CacheKeyManager;
import com.expfool.libs.cachelib.manager.ttl.CacheTtlManager;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

@Slf4j
@RequiredArgsConstructor
public abstract class AbstractTtlCacheManager<K, V> implements CacheManager<K, V> {

    private final TtlCacheAdapter<K, V> cacheAdapter;
    private final CacheKeyManager<K> keyManager;
    private final CacheTtlManager ttlManager;
    private final String cacheName;

    @Override
    public Optional<V> get(K key) {
        var cacheKey = keyManager.getCacheKey(cacheName, key);
        return cacheAdapter.get(cacheKey);
    }

    @Override
    public List<V> get(Collection<K> keys) {
        var cacheKeys = keys.stream().map(key -> keyManager.getCacheKey(cacheName, key)).toList();
        return cacheAdapter.get(cacheKeys);
    }

    @Override
    public Optional<V> get(K key, Supplier<V> supplier) {
        var cacheKey = keyManager.getCacheKey(cacheName, key);
        var cachedData = cacheAdapter.get(cacheKey);
        if (cachedData.isPresent()) {
            return cachedData;
        }
        var newData = supplier.get();
        cacheAdapter.put(cacheKey, newData, ttlManager.getTtl(cacheName));
        return Optional.of(newData);
    }

    @Override
    public void put(K key, V value) {
        var cacheKey = keyManager.getCacheKey(cacheName, key);
        cacheAdapter.put(cacheKey, value, ttlManager.getTtl(cacheName));
    }

    @Override
    public void remove(K key) {
        var cacheKey = keyManager.getCacheKey(cacheName, key);
        cacheAdapter.remove(cacheKey);
    }
}
