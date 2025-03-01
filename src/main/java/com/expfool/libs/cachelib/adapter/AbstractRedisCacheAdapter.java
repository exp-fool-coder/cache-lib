package com.expfool.libs.cachelib.adapter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;

import java.time.Duration;

import static java.util.Objects.isNull;

@Slf4j
public abstract class AbstractRedisCacheAdapter<V> extends AbstractRedisReadonlyCacheAdapter<V> implements TtlCacheAdapter<String, V> {

    public AbstractRedisCacheAdapter(RedisTemplate<String, V> redisTemplate) {
        super(redisTemplate);
    }

    @Override
    public void put(String key, V value) {
        put(key, value, null);
    }

    @Override
    public void put(String key, V value, Duration ttl) {
        try {
            redisTemplate.delete(key);
            if (isNull(ttl)) {
                redisTemplate.opsForValue().set(key, value);
            } else {
                redisTemplate.opsForValue().set(key, value, ttl);
            }
        } catch (Exception exception) {
            log.error("Error while putting data to redis", exception);
        }
    }

    @Override
    public void remove(String key) {
        try {
            redisTemplate.delete(key);
        } catch (Exception exception) {
            log.error("Error while removing cached data from redis", exception);
        }
    }
}
