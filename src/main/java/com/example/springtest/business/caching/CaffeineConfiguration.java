package com.example.springtest.business.caching;

import com.example.springtest.core.caching.CacheConfiguration;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;
//TODO not working
@Configuration()
//@ConditionalOnProperty(name = "cache.profile", havingValue = "caffeine")
public class CaffeineConfiguration implements CacheConfiguration {
    @Value("${ttl}")
    private long ttl;
    private final CacheManager cacheManager;

    public CaffeineConfiguration() {
        this.cacheManager = new CaffeineCacheManager();

        // Customize the Caffeine configurations as needed
        ((CaffeineCacheManager) cacheManager).setCaffeine(Caffeine.newBuilder()
                .maximumSize(500)
                .expireAfterAccess(ttl, TimeUnit.SECONDS));
    }

    @Override
    public CacheManager cacheManager() {
        return this.cacheManager;
    }

    @Override
    public void clearAllCaches() {
        cacheManager.getCacheNames().forEach(cacheName -> cacheManager.getCache(cacheName).clear());
    }

    @Override
    public void evictCacheByKey(String cacheName, Object key) {
        Cache cache = cacheManager.getCache(cacheName);
        if (cache != null) {
            cache.evict(key);
        }
    }

    @Override
    public void clearCacheByName(String cacheName) {
        Cache cache = cacheManager.getCache(cacheName);
        if (cache != null) {
            cache.clear();
        }
    }

    @Override
    public void addToCache(String cacheName, Object key, Object value) {
        Cache cache = cacheManager.getCache(cacheName);
        if (cache != null) {
            cache.put(key, value);
        }
    }

    @Override
    public void updateCache(String cacheName, Object key, Object value) {
        evictCacheByKey(cacheName, key); // Remove existing value
        addToCache(cacheName, key, value); // Add updated value
    }

    @Override
    public <T> T getFromCache(String cacheName, Object key, Class<T> type) {
        Cache cache = cacheManager.getCache(cacheName);
        if (cache != null) {
            return cache.get(key, type);
        }
        return null;
    }
}