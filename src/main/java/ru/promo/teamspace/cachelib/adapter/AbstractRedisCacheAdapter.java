package ru.promo.teamspace.cachelib.adapter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;

import java.time.Duration;
import java.util.Optional;

import static java.util.Objects.isNull;
import static java.util.Optional.empty;
import static java.util.Optional.ofNullable;

@Slf4j
public abstract class AbstractRedisCacheAdapter<V> implements TtlCacheAdapter<String, V> {

    private final RedisTemplate<String, V> redisTemplate;

    public AbstractRedisCacheAdapter(RedisTemplate<String, V> redisTemplate) {
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
