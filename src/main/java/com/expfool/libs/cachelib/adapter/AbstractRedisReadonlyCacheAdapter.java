package com.expfool.libs.cachelib.adapter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static java.util.Optional.empty;
import static java.util.Optional.ofNullable;

@Slf4j
public class AbstractRedisReadonlyCacheAdapter<V> implements ReadonlyCacheAdapter<String, V> {

    protected final RedisTemplate<String, V> redisTemplate;

    public AbstractRedisReadonlyCacheAdapter(RedisTemplate<String, V> redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.redisTemplate.afterPropertiesSet();
    }

    @Override
    public Optional<V> get(String key) {
        try {
            var data = redisTemplate.opsForValue().get(key);
            return ofNullable(data);
        } catch (Exception exception) {
            log.error("Error while getting cached data from redis", exception);
            return empty();
        }
    }

    @Override
    public List<V> get(Collection<String> keys) {
        try {
            return redisTemplate.opsForValue().multiGet(keys);
        } catch (Exception exception) {
            log.error("Error while getting cached data from redis", exception);
            return List.of();
        }
    }
}
