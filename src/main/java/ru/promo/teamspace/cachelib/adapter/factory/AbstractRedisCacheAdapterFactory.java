package ru.promo.teamspace.cachelib.adapter.factory;

import org.springframework.data.redis.core.RedisTemplate;
import ru.promo.teamspace.cachelib.adapter.CacheAdapter;

public abstract class AbstractRedisCacheAdapterFactory<K, V> implements CacheAdapterFactory<K, V> {

    private final RedisTemplate<K, V> redisTemplate;

    public AbstractRedisCacheAdapterFactory(RedisTemplate<K, V> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public abstract CacheAdapter<K, V> getCacheAdapter();
}
