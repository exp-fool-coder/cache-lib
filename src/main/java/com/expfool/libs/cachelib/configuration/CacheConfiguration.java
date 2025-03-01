package com.expfool.libs.cachelib.configuration;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;
import com.expfool.libs.cachelib.configuration.property.CacheProperties;
import com.expfool.libs.cachelib.manager.key.impl.BaseStringCacheKeyManager;
import com.expfool.libs.cachelib.manager.key.CacheKeyManager;
import com.expfool.libs.cachelib.manager.ttl.BaseCacheTtlManager;
import com.expfool.libs.cachelib.manager.ttl.CacheTtlManager;

@Data
@Validated
@Configuration
@EnableConfigurationProperties(CacheProperties.class)
@RequiredArgsConstructor
public class CacheConfiguration {

    @Value("${spring.application.name}")
    private String applicationName;

    @Bean
    public CacheKeyManager<String> baseStringCacheKeyManager(CacheProperties cacheProperties) {
        return new BaseStringCacheKeyManager(cacheProperties, applicationName);
    }

    @Bean
    public CacheTtlManager baseTtlManager(CacheProperties cacheProperties) {
        return new BaseCacheTtlManager(cacheProperties);
    }
}
