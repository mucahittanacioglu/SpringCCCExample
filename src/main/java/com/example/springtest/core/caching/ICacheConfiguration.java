package com.example.springtest.core.caching;

import org.springframework.cache.CacheManager;


public interface ICacheConfiguration {

    CacheManager cacheManager();

    void clearAllCaches();

    void evictCacheByKey(String cacheName, Object key);

    void clearCacheByName(String cacheName);

    void addToCache(String cacheName, Object key, Object value);

    void updateCache(String cacheName, Object key, Object value);

    <T> T getFromCache(String cacheName, Object key, Class<T> type);

}