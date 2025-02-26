package ru.promo.teamspace.cachelib.manager.key;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.promo.teamspace.cachelib.configuration.property.CacheProperties;

import static java.util.Objects.isNull;
import static ru.promo.teamspace.cachelib.ExceptionUtils.cachePropertiesNotFound;

@Slf4j
@RequiredArgsConstructor
public class BaseStringCacheKeyManager implements CacheKeyManager<String> {

    private final CacheProperties cacheProperties;

    @Override
    public String getCacheKey(String cacheGroup, String keySuffix) {
        var cacheProperty = cacheProperties.getCache().get(cacheGroup);
        if (isNull(cacheProperty)) {
            log.error("Not specified cache properties for cache: {}", cacheGroup);
            throw cachePropertiesNotFound(cacheGroup);
        }
        var cacheName = cacheProperty.getKeyPrefix();
        var keyPrefix = cacheProperties.getCachePrefix();
        return keyPrefix + "." + cacheName + "." + keySuffix;
    }
}
