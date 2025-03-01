package com.expfool.libs.cachelib.manager;

import lombok.RequiredArgsConstructor;
import com.expfool.libs.cachelib.adapter.ReadonlyCacheAdapter;
import com.expfool.libs.cachelib.manager.key.CacheKeyManager;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public abstract class AbstractReadOnlyCacheManager<K, V> implements ReadonlyCacheManager<K, V> {

    protected final ReadonlyCacheAdapter<K, V> adapter;
    protected final CacheKeyManager<K> keyManager;
    protected final String cacheName;

    @Override
    public Optional<V> get(K key) {
        var cacheKey = keyManager.getCacheKey(cacheName, key);
        return adapter.get(cacheKey);
    }

    @Override
    public List<V> get(Collection<K> keys) {
        var cacheKeys = keys.stream().map(key -> keyManager.getCacheKey(cacheName, key)).toList();
        return adapter.get(cacheKeys);
    }
}
