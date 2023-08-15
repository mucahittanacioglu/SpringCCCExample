package com.example.springtest.core.caching;

import com.example.springtest.core.caching.CacheAction;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;



@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Cacheable {
    String cacheName();
    String key() default "";
    String keyExpression() default "";
    CacheAction action() default CacheAction.READ;
}