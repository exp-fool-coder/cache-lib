package ru.promo.teamspace.cachelib.manager.factory;

import ru.promo.teamspace.cachelib.manager.CacheManager;

public interface CacheManagerFactory<K, V> {

    CacheManager<K, V> getCacheManager();
}
