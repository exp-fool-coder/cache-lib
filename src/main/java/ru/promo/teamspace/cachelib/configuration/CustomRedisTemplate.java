package ru.promo.teamspace.cachelib.configuration;

import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

public class CustomRedisTemplate<K, V> extends RedisTemplate<K, V> {

    public CustomRedisTemplate(
            RedisConnectionFactory connectionFactory,
            RedisSerializer<K> keySerializer,
            RedisSerializer<V> valueSerializer
    ) {
        super.setConnectionFactory(connectionFactory);

        super.setKeySerializer(keySerializer);
        super.setHashKeySerializer(keySerializer);

        super.setValueSerializer(valueSerializer);
        super.setHashValueSerializer(valueSerializer);
    }
}
