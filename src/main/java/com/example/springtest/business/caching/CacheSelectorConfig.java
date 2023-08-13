package com.example.springtest.business.caching;

import com.example.springtest.core.caching.CacheConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

@Configuration
@EnableCaching
public class CacheSelectorConfig {
    @Autowired
    private ApplicationContext applicationContext;

    @Value("${cache.profile}")
    private String cacheProfile;

    @Bean
    public CacheConfiguration cacheConfiguration() {
        if ("redis".equalsIgnoreCase(cacheProfile)) {
            return new RedisConfiguration();
        } else if ("caffeine".equalsIgnoreCase(cacheProfile)) {
            return new CaffeineConfiguration();
        }
        throw new IllegalArgumentException("Invalid cache profile: " + cacheProfile);
    }

}