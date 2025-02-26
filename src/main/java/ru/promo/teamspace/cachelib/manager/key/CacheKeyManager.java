package ru.promo.teamspace.cachelib.manager.key;

public interface CacheKeyManager<T> {

    T getCacheKey(String cacheGroup, T keySuffix);
}
