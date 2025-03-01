package com.expfool.libs.cachelib.manager.impl;

import com.expfool.libs.cachelib.adapter.TtlCacheAdapter;
import com.expfool.libs.cachelib.manager.AbstractTtlCacheManager;
import com.expfool.libs.cachelib.manager.key.CacheKeyManager;
import com.expfool.libs.cachelib.manager.ttl.CacheTtlManager;

public class TtlCacheManager<T> extends AbstractTtlCacheManager<String, T> {


    public TtlCacheManager(TtlCacheAdapter<String, T> cacheAdapter, CacheKeyManager<String> keyManager,
                           CacheTtlManager ttlManager, String cacheName) {
        super(cacheAdapter, keyManager, ttlManager, cacheName);
    }
}
