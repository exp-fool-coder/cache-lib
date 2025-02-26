package ru.promo.teamspace.cachelib.adapter.factory;

import org.springframework.data.redis.core.RedisTemplate;
import ru.promo.teamspace.cachelib.adapter.CacheAdapter;

public class StringKeyRedisCacheAdapterFactory<V> extends AbstractRedisCacheAdapterFactory<String, V> {

    public StringKeyRedisCacheAdapterFactory(RedisTemplate<String, V> redisTemplate) {
        super(redisTemplate);
    }

    @Override
    public CacheAdapter<String, V> getCacheAdapter() {
        return null;
    }
}
