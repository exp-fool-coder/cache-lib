# cache-lib

 Содержит в себе набор интерфейсов и их реализаций для быстрого подключение кэша.
 
## Структура

### Интерфейсы

#### Адаптеры

Набор интерфейсов для не посредственного взаимодействия с хранилищем кэша

- `ReadonlyCacheAdapter<K, V>`
  - `Optional<V> get(K key)`
  - `List<V> get(Collection<K> keys)`
- `CacheAdapter<K, V>`
  - Все методы `ReadonlyCacheAdapter<K, V>`
  - `void put(K key, V value)`
  - `void remove(K key)`
- `TtlCacheAdapter<K, V>`
  - Все методы `CacheAdapter<K, V>`
  - `void put(K key, V value, Duration ttl)`

##### Абстрактные реализации адаптеров для Redis

Данные реализации взаимодействуют с Redis c помощью `RedisTemplate<V, K>`

- `AbstractRedisReadonlyCacheAdapter<V>`

    Реализует интерфейс `ReadonlyCacheAdapter<String, V>`в 
    
    Содержит в себе private поле `RedisTemplate<String, V> redisTemplate`

    Конструктор: `AbstractRedisReadonlyCacheAdapter(RedisTemplate<String, V> redisTemplate)`
- `AbstractRedisCacheAdapter<V>`
  
    Наследует `AbstractRedisReadonlyCacheAdapter<V>` и реализует интерфейс `TtlCacheAdapter<String, V>`
    
    Конструктор: `AbstractRedisCacheAdapter(RedisTemplate<String, V> redisTemplate)`

##### Реализации адаптеров
- `StringKeyRedisReadonlyAdapter<V>`
    
    Наследует `AbstractRedisReadonlyCacheAdapter<V>`
    
    Конструктор `StringKeyRedisReadonlyAdapter(RedisTemplate<String, V> redisTemplate)`
- `StringKeyRedisAdapter<V>`

    Наследует `AbstractRedisCacheAdapter<V>`

    Конструктор `StringKeyRedisAdapter(RedisTemplate<String, V> redisTemplate)`

#### Менеджеры ключей

##### Интерефейсы
- `CacheKeyManager<T>`
  - `T getCacheKey(String cacheGroup, T keySuffix)`
##### Реализации
- `BaseStringCacheKeyManager`
    
    Реализует `CacheKeyManager<String>`
  
    Конструктор `BaseStringCacheKeyManager(CacheProperties cacheProperties, String applicationName)`

    Использует application properties для получения данных для составления строки ключа
- `GlobalStringCacheKeyManager`

    Реализует `CacheKeyManager<String>`
    
    Имеет пустой конструктор. 

    Cоставляет ключ простой конкатенацией `cacheGroup + "." + keyPrefix`.

#### Менеджер TTL
##### Интерфейсы
- `CacheTtlManager`
    - `Duration getTtl(String cacheName)`
##### Реализации

- `BaseCacheTtlManager`

    Реализует `CacheTtlManager`

    Конструктор `BaseCacheTtlManager(CacheProperties cacheProperties)`
    
    Достаёт данные о длительности жизни кэша из application properties на основе `cacheName` 

#### Менеджеры кэша
Основные интерфейсы для работы с кэшом:
 - `ReadonlyCacheManager<K, V>`
   - `Optional<V> get(K key)`
   - `List<V> get(Collection<K> keys)`
 - `CacheManager<K, V>`
   - Все методы `ReadonlyCacheManager<K, V>`
   - `Optional<V> get(K key, Supplier<V> supplier)`
   - `void put(K key, V value)`
   - `void remove(K key)`
 
##### Абстрактные реализации менеджеров для Redis

Данные реализации используют адаптеры для взаимодействия с хранилищем кэша.

- `AbstractReadOnlyCacheManager<K, V>`
  
    Реализует `ReadonlyCacheManager<K, V>`

    Конструктор `AbstractReadOnlyCacheManager(ReadonlyCacheAdapter<K, V> adapter, CacheKeyManager<K> keyManager, String cacheName)`

    Реализует взаимодействие с кэшом используя `ReadonlyCacheAdapter<K, V>`

    Формирует ключи с помощью `CacheKeyManager<K>`

    Также при создании экземпляра надо указать имя кэша

-  `AbstractTtlCacheManager<K, V> `

    Реализует `CacheManager<K, V>`

    Конструктор `AbstractTtlCacheManager(TtlCacheAdapter<K, V> cacheAdapter, CacheKeyManager<K> keyManager, CacheTtlManager ttlManager, String cacheName)`

    Реализует взаимодействие кэшом используя `TtlCacheAdapter<K, V>`

    Формирует ключи с помощью `CacheKeyManager<K>`

    Получает данные о длительности жизни кэша с помощью `CacheTtlManager`

   Также при создании экземпляра надо указать имя кэша

##### Реализации менеджеров

- `ReadonlyCacheManagerImpl<K, V>`

    Реализует `AbstractReadOnlyCacheManager<K, V>`

    Конструктор `ReadonlyCacheManagerImpl(ReadonlyCacheAdapter<K, V> adapter, CacheKeyManager<K> keyManager, String cacheName)`

- `TtlCacheManager<T>`

    Реализует `AbstractTtlCacheManager<String, T>`

    Конструктор `ReadonlyCacheManagerImpl(ReadonlyCacheAdapter<K, V> adapter, CacheKeyManager<K> keyManager, String cacheName)`
    
### Структура properties

Все переменные окружения хранятся в бине CacheProperties

Префикс всех пропертей `cacher`

- `CacheProperies`
  - `cachePrefix` Строковый префикс для всех кэшей использующих `BaseStringCacheKeyManager`. Обязателен для указания.
  - `cache`
    
    тип `Map<String, CacheProperty>`
        
    - `CachePropery`
      - `Duration ttl` длительность жизни кэша использующих `BaseCacheTtlManager`
      - `String keyPrefix` префикс конкретного кэш менеджера использующего `BaseStringCacheKeyManager`

#### Пример (для application.yml)
```yaml
cacher:
  cache-prefix: project
  cache:
    entity-one:
      ttl: PT90M
      key-prefix: entity-one
    entity-two:
      ttl: PT90M
      key-prefix: entity-two
```

### Полезные штуки
    
Имеется класс для более простого создания `RedisTemplate<K,V>`

     CustomRedisTemplate(
            RedisConnectionFactory connectionFactory,
            RedisSerializer<K> keySerializer,
            RedisSerializer<V> valueSerializer)

### Что готовое предоставляет конфигурация стартера

- applicationName - имя приложения spring из `spring.application.name`
- CacheProperties - набор пропертей кэша
- baseStringCacheKeyManager - бин с типом CacheKeyManager<String>, создающий экземпляр BaseStringCacheKeyManager
- baseTtlManager - бин с типом CacheTtlManager, создающий экземпляр BaseCacheTtlManager

## Пример использования

- Заводим в application.yml проперти

```yaml
cacher:
  cache-prefix: project
  cache:
    some-object:
      ttl: PT90M
      key-prefix: object-prefix
```

- Конфигурируем бин менеджера

```java
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import adapter.com.expfool.libs.cachelib.TtlCacheAdapter;
import impl.adapter.com.expfool.libs.cachelib.StringKeyRedisAdapter;
import configuration.com.expfool.libs.cachelib.CustomRedisTemplate;

@Configuration
@RequiredArgsConstructor
public class RedisConfiguration {

    private final LettuceConnectionFactory lettuceConnectionFactory;
    private final ObjectMapper objectMapper;

    @Bean
    public RedisTemplate<String, OurObject> ourObjectRedisTemplate(LettuceConnectionFactory lettuceConnectionFactory,
                                                                   ObjectMapper objectMapper) {
        var keySerializer = new StringRedisSerializer();
        var valueSerializer = new Jackson2JsonRedisSerializer<>(objectMapper, OurObject.class);
        return new CustomRedisTemplate<>(lettuceConnectionFactory, keySerializer, valueSerializer);
    }

    @Bean
    public TtlCacheAdapter<String, OurObject> ourObjectTtlCacheAdapter(RedisTemplate<String, OurObject> redisTemplate) {
        return new StringKeyRedisAdapter<>(redisTemplate);
    }

    @Bean

    public CacheManager<String, Project> projectTtlCacheManager(TtlCacheAdapter<String, OurObject> adapter,
                                                                CacheKeyManager<String> cacheKeyManager,
                                                                CacheTtlManager cacheTtlManager) {
        return new TtlCacheManager<>(adapter, cacheKeyManager, cacheTtlManager, "some-object");
    }
}
```

Теперь мы можем инджектить кэш менеджер в наши бины:

```java
@Autowired
private CacheManager<String, Project> cacheManager;
```