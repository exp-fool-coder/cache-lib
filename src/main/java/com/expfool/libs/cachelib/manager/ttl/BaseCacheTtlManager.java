package com.expfool.libs.cachelib.manager.ttl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.expfool.libs.cachelib.configuration.property.CacheProperties;

import java.time.Duration;

import static java.util.Objects.isNull;
import static com.expfool.libs.cachelib.ExceptionUtils.cachePropertiesNotFound;

@Slf4j
@RequiredArgsConstructor
public class BaseCacheTtlManager implements CacheTtlManager{

    private final CacheProperties cacheProperties;

    @Override
    public Duration getTtl(String cacheName) {
        var cacheProperty  = cacheProperties.getCache().get(cacheName);
        if (isNull(cacheProperty)) {
            log.error("Not specified cache properties for cache: {}", cacheName);
            throw cachePropertiesNotFound(cacheName);
        }
        return cacheProperty.getTtl();
    }
}
