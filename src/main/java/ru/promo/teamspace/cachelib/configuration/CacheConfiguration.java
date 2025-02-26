package ru.promo.teamspace.cachelib.configuration;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;
import ru.promo.teamspace.cachelib.configuration.property.CacheProperties;
import ru.promo.teamspace.cachelib.manager.key.BaseStringCacheKeyManager;
import ru.promo.teamspace.cachelib.manager.key.CacheKeyManager;

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
        return new BaseStringCacheKeyManager(cacheProperties);
    }
}
