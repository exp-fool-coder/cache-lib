package com.expfool.libs.cachelib.manager.key.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.expfool.libs.cachelib.configuration.property.CacheProperties;
import com.expfool.libs.cachelib.manager.key.CacheKeyManager;

import static java.util.Objects.isNull;
import static com.expfool.libs.cachelib.ExceptionUtils.cachePropertiesNotFound;

@Slf4j
@RequiredArgsConstructor
public class BaseStringCacheKeyManager implements CacheKeyManager<String> {

    private final CacheProperties cacheProperties;
    private final String applicationName;

    @Override
    public String getCacheKey(String cacheGroup, String keySuffix) {
        var cacheProperty = cacheProperties.getCache().get(cacheGroup);
        if (isNull(cacheProperty)) {
            log.error("Not specified cache properties for cache: {}", cacheGroup);
            throw cachePropertiesNotFound(cacheGroup);
        }
        var cacheName = cacheProperty.getKeyPrefix();
        var keyPrefix = cacheProperties.getCachePrefix();
        return keyPrefix + "." + applicationName + "." + cacheName + "." + keySuffix;
    }
}
