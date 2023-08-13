package com.example.springtest.business.caching;

import com.example.springtest.core.caching.CacheConfiguration;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.cache.CacheManager;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Set;
import java.util.concurrent.TimeUnit;
@Configuration()
//@ConditionalOnProperty(name = "cache.profile", havingValue = "redis")
public class RedisConfiguration implements CacheConfiguration {

    @Value("${redis.host}")
    private String redisHost;

    @Value("${redis.port}")
    private int redisPort;
    @Value("${ttl}")
    private long ttl;
    //TODO calling bean's function might be problem check.
    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        return new LettuceConnectionFactory(redisHost, redisPort);
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory());

        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.activateDefaultTyping(LaissezFaireSubTypeValidator.instance, ObjectMapper.DefaultTyping.NON_FINAL);

        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(om,Object.class);

        template.setValueSerializer(jackson2JsonRedisSerializer);
        template.setKeySerializer(new StringRedisSerializer());
        template.setHashKeySerializer(new StringRedisSerializer());
        template.afterPropertiesSet();
        return template;

    }

    @Override
    public CacheManager cacheManager() {
        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofSeconds(ttl));

        return RedisCacheManager.builder(redisConnectionFactory())
                .cacheDefaults(config)
                .build();
    }

    @Override
    public void clearAllCaches() {
        redisTemplate().execute((RedisCallback<Object>) (connection) -> {
            connection.flushDb();
            return null;
        });
    }

    @Override
    public void evictCacheByKey(String cacheName, Object key) {
        redisTemplate().delete(cacheName + "::" + key);
    }

    @Override
    public void clearCacheByName(String cacheName) {
        Set<String> keys = redisTemplate().keys(cacheName + "::" + "*");
        if (keys != null && !keys.isEmpty()) {
            redisTemplate().delete(keys);
        }
    }

    @Override
    public void addToCache(String cacheName, Object key, Object value) {
        redisTemplate().opsForValue().set(cacheName + "::" + key, value,ttl,TimeUnit.SECONDS);
    }

    @Override
    public void updateCache(String cacheName, Object key, Object value) {
        addToCache(cacheName, key, value);  // in Redis, update is also a put operation
    }

    @Override
    public <T> T getFromCache(String cacheName, Object key, Class<T> type) {
        return (T) redisTemplate().opsForValue().get(cacheName + "::" + key);
    }
}
