package com.example.springtest.business.logging;

import com.example.springtest.core.logging.ILoggerConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoggerConfig {


    @Bean
    @ConditionalOnProperty(name = "logger.profile", havingValue = "log4j")
    public ILoggerConfiguration log4j() {
        return new Log4j2LoggerConfiguration();
    }

    @Bean
    @ConditionalOnProperty(name = "logger.profile", havingValue = "util")
    public ILoggerConfiguration javUtilLogger() {
        return new JavaUtilLoggerConfiguration();
    }
}