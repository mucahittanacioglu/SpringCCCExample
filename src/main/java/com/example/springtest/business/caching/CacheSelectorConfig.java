//package com.example.springtest.business.caching;
//
//import com.example.springtest.core.caching.ICacheConfiguration;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//
//import org.springframework.cache.annotation.EnableCaching;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//@EnableCaching
//public class CacheSelectorConfig {
//    @Autowired
//    private ApplicationContext applicationContext;
//
//    @Value("${cache.profile}")
//    private String cacheProfile;
//
//    @Bean
//    public ICacheConfiguration cacheConfiguration() {
//        if ("redis".equalsIgnoreCase(cacheProfile)) {
//            return new RedisConfigurationI();
//        }
////        else if ("caffeine".equalsIgnoreCase(cacheProfile)) {
////            return new CaffeineConfiguration();
////        }
//        else
//            throw new IllegalArgumentException("Invalid cache profile: " + cacheProfile);
//    }
//
//}