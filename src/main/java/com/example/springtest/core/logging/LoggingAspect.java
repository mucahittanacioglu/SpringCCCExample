package com.example.springtest.core.logging;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {

    @Pointcut("@annotation(Log)")
    public void logPointcut(){
    }

    @Around("logPointcut()")
    public void logAllMethodCallsAdvice(ProceedingJoinPoint joinPoint){
        System.out.println("In Aspect");
    }
}
