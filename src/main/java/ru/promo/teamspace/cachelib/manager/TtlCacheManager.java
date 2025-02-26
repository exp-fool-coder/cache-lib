package ru.promo.teamspace.cachelib.manager;

import ru.promo.teamspace.cachelib.adapter.TtlCacheAdapter;
import ru.promo.teamspace.cachelib.configuration.property.CacheProperties;
import ru.promo.teamspace.cachelib.manager.key.CacheKeyManager;

public class TtlCacheManager<T> extends AbstractTtlCacheManager<String, T> {


    public TtlCacheManager(TtlCacheAdapter<String, T> cacheAdapter, CacheKeyManager<String> keyManager,
                           CacheProperties cacheProperties, String cacheName) {
        super(cacheAdapter, keyManager, cacheProperties, cacheName);
    }
}
