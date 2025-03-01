package com.expfool.libs.cachelib.manager.ttl;

import java.time.Duration;

public interface CacheTtlManager {

    Duration getTtl(String cacheName);
}
