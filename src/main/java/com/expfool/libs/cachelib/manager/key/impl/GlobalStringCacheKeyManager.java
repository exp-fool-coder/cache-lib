package com.expfool.libs.cachelib.manager.key.impl;

import com.expfool.libs.cachelib.manager.key.CacheKeyManager;

public class GlobalStringCacheKeyManager implements CacheKeyManager<String> {

    @Override
    public String getCacheKey(String cacheGroup, String keySuffix) {
        return cacheGroup + "." + keySuffix;
    }
}
