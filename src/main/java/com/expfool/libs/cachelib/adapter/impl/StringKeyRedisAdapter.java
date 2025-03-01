package com.expfool.libs.cachelib.adapter.impl;

import org.springframework.data.redis.core.RedisTemplate;
import com.expfool.libs.cachelib.adapter.AbstractRedisCacheAdapter;

public class StringKeyRedisAdapter<V> extends AbstractRedisCacheAdapter<V> {

    public StringKeyRedisAdapter(RedisTemplate<String, V> redisTemplate) {
        super(redisTemplate);
    }
}
