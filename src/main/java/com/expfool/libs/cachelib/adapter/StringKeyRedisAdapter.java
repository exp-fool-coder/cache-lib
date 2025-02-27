package com.expfool.libs.cachelib.adapter;

import org.springframework.data.redis.core.RedisTemplate;

public class StringKeyRedisAdapter<T> extends AbstractRedisCacheAdapter<T> {

    public StringKeyRedisAdapter(RedisTemplate<String, T> redisTemplate) {
        super(redisTemplate);
    }
}
