package com.expfool.libs.cachelib.adapter.factory;

import org.springframework.data.redis.core.RedisTemplate;
import com.expfool.libs.cachelib.adapter.CacheAdapter;

public class StringKeyRedisCacheAdapterFactory<V> extends AbstractRedisCacheAdapterFactory<String, V> {

    public StringKeyRedisCacheAdapterFactory(RedisTemplate<String, V> redisTemplate) {
        super(redisTemplate);
    }

    @Override
    public CacheAdapter<String, V> getCacheAdapter() {
        return null;
    }
}
