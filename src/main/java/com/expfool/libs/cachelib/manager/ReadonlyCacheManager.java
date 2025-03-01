package com.expfool.libs.cachelib.manager;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface ReadonlyCacheManager<K, V> {

    Optional<V> get(K key);

    List<V> get(Collection<K> keys);
}
