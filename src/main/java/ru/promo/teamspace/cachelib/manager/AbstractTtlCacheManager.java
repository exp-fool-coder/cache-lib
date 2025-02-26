package ru.promo.teamspace.cachelib.manager;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.promo.teamspace.cachelib.adapter.TtlCacheAdapter;
import ru.promo.teamspace.cachelib.configuration.property.CacheProperties;
import ru.promo.teamspace.cachelib.manager.key.CacheKeyManager;

import java.time.Duration;
import java.util.Optional;
import java.util.function.Supplier;

import static java.util.Objects.isNull;
import static ru.promo.teamspace.cachelib.ExceptionUtils.cachePropertiesNotFound;

@Slf4j
@RequiredArgsConstructor
public abstract class AbstractTtlCacheManager<K, V> implements CacheManager<K, V> {

    private final TtlCacheAdapter<K, V> cacheAdapter;
    private final CacheKeyManager<K> keyManager;
    private final CacheProperties cacheProperties;
    private final String cacheName;

    @Override
    public Optional<V> get(K key) {
        var cacheKey = keyManager.getCacheKey(getCacheName(), key);
        return cacheAdapter.get(cacheKey);
    }

    @Override
    public Optional<V> get(K key, Supplier<V> supplier) {
        var cacheKey = keyManager.getCacheKey(getCacheName(), key);
        var cachedData = cacheAdapter.get(cacheKey);
        if (cachedData.isPresent()) {
            return cachedData;
        }
        var newData = supplier.get();
        cacheAdapter.put(cacheKey, newData, getTtl());
        return Optional.of(newData);
    }

    @Override
    public void put(K key, V value) {
        var cacheKey = keyManager.getCacheKey(getCacheName(), key);
        cacheAdapter.put(cacheKey, value, getTtl());
    }

    @Override
    public void remove(K key) {
        var cacheKey = keyManager.getCacheKey(getCacheName(), key);
        cacheAdapter.remove(cacheKey);
    }

    private Duration getTtl() {
        var cacheProperty  = cacheProperties.getCache().get(getCacheName());
        if (isNull(cacheProperty)) {
            log.error("Not specified cache properties for cache: {}", getCacheName());
            throw cachePropertiesNotFound(getCacheName());
        }
        return cacheProperty.getTtl();
    }

    private String getCacheName() {
        return cacheName;
    }
}
