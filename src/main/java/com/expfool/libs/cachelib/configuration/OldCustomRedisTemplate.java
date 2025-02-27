package com.expfool.libs.cachelib.configuration;

import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;

public class OldCustomRedisTemplate<V> extends RedisTemplate<String, V> {

    public OldCustomRedisTemplate(LettuceConnectionFactory lettuceConnectionFactory,
                                  ObjectMapper objectMapper, Class<V> type) {
        super.setConnectionFactory(lettuceConnectionFactory);

        var keySerializer = new StringRedisSerializer();
        super.setKeySerializer(keySerializer);
        super.setHashKeySerializer(keySerializer);

        var valueSerializer = new Jackson2JsonRedisSerializer<>(objectMapper, type);
        super.setValueSerializer(valueSerializer);
        super.setHashValueSerializer(valueSerializer);
    }
}
