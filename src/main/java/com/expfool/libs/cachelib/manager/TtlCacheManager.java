package com.expfool.libs.cachelib.manager;

import com.expfool.libs.cachelib.adapter.TtlCacheAdapter;
import com.expfool.libs.cachelib.configuration.property.CacheProperties;
import com.expfool.libs.cachelib.manager.key.CacheKeyManager;

public class TtlCacheManager<T> extends AbstractTtlCacheManager<String, T> {


    public TtlCacheManager(TtlCacheAdapter<String, T> cacheAdapter, CacheKeyManager<String> keyManager,
                           CacheProperties cacheProperties, String cacheName) {
        super(cacheAdapter, keyManager, cacheProperties, cacheName);
    }
}
