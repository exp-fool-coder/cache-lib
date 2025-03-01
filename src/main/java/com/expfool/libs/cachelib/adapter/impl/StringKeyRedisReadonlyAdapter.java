package com.expfool.libs.cachelib.adapter.impl;

import org.springframework.data.redis.core.RedisTemplate;
import com.expfool.libs.cachelib.adapter.AbstractRedisReadonlyCacheAdapter;

public class StringKeyRedisReadonlyAdapter<V> extends AbstractRedisReadonlyCacheAdapter<V> {
    public StringKeyRedisReadonlyAdapter(RedisTemplate<String, V> redisTemplate) {
        super(redisTemplate);
    }
}
