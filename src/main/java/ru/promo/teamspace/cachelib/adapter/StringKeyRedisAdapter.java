package ru.promo.teamspace.cachelib.adapter;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

public class StringKeyRedisAdapter<T> extends AbstractRedisCacheAdapter<T> {

    public StringKeyRedisAdapter(RedisTemplate<String, T> redisTemplate) {
        super(redisTemplate);
    }
}
