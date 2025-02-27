package com.expfool.libs.cachelib.manager.factory;

import com.expfool.libs.cachelib.manager.CacheManager;

public interface CacheManagerFactory<K, V> {

    CacheManager<K, V> getCacheManager();
}
