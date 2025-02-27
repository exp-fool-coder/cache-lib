package com.expfool.libs.cachelib.configuration.property;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.Duration;

@Data
public class CacheProperty {

    @NotNull
    private Duration ttl;

    @NotEmpty
    private String keyPrefix;
}
