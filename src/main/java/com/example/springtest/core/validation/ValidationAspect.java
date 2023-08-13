package com.example.springtest.core.validation;

import com.example.springtest.core.entities.IEntity;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ValidationAspect {

    @Autowired
    private ApplicationContext applicationContext;

    @Around("@annotation(validateEntity)")
    public Object validateWithValidator(ProceedingJoinPoint joinPoint, Validate validateEntity) throws Throwable {
        IValidator<IEntity> validator = (IValidator<IEntity>) applicationContext.getBean(validateEntity.value());

        Object[] args = joinPoint.getArgs();
        if (args.length > 0 && args[0] instanceof IEntity) {
            validator.validate((IEntity) args[0]);
        }
        return joinPoint.proceed();
    }
}