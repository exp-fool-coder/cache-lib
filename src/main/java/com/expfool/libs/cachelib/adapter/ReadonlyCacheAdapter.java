package com.expfool.libs.cachelib.adapter;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface ReadonlyCacheAdapter<K, V> {

    Optional<V> get(K key);

    List<V> get(Collection<K> keys);
}
