package com.expfool.libs.cachelib.adapter.factory;

import com.expfool.libs.cachelib.adapter.CacheAdapter;

public interface CacheAdapterFactory<K, V> {

    CacheAdapter<K, V> getCacheAdapter();
}
