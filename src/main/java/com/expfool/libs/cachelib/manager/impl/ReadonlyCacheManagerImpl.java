package com.expfool.libs.cachelib.manager.impl;

import com.expfool.libs.cachelib.adapter.ReadonlyCacheAdapter;
import com.expfool.libs.cachelib.manager.AbstractReadOnlyCacheManager;
import com.expfool.libs.cachelib.manager.key.CacheKeyManager;

public class ReadonlyCacheManagerImpl<K, V> extends AbstractReadOnlyCacheManager<K, V> {
    public ReadonlyCacheManagerImpl(ReadonlyCacheAdapter<K, V> adapter, CacheKeyManager<K> keyManager, String cacheName) {
        super(adapter, keyManager, cacheName);
    }
}
