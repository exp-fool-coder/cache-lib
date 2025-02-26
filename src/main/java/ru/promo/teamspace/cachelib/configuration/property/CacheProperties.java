package ru.promo.teamspace.cachelib.configuration.property;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.validation.annotation.Validated;

import java.util.HashMap;
import java.util.Map;

@Data
@Validated
@ConfigurationProperties(prefix = "cacher")
public class CacheProperties {

    @Valid
    @NestedConfigurationProperty
    private Map<String, CacheProperty> cache = new HashMap<>();

    @NotEmpty
    private String cachePrefix;
}
