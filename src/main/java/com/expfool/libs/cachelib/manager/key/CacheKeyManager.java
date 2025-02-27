package com.expfool.libs.cachelib.manager.key;

public interface CacheKeyManager<T> {

    T getCacheKey(String cacheGroup, T keySuffix);
}
