package ru.promo.teamspace.cachelib.adapter.factory;

import ru.promo.teamspace.cachelib.adapter.CacheAdapter;

public interface CacheAdapterFactory<K, V> {

    CacheAdapter<K, V> getCacheAdapter();
}
