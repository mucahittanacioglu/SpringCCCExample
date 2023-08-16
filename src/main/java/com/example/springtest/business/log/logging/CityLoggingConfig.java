package com.example.springtest.business.log.logging;

import com.ts.core.logging.ILogging;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;

import java.util.Arrays;
@Component
public class CityLoggingConfig extends ILogging {
    public CityLoggingConfig() {
        super();
    }

    @Override
    public String debug() {
        return super.getIsExecuted() ? "This is before method execution":"This is after method execution";
    }

    @Override
    public String info() {
        ProceedingJoinPoint joinPoint = super.getJoinPoint();// We can access methods parameters with this object.
        var method = joinPoint.getArgs();
        String methodName = joinPoint.getSignature().getName();
        return super.getIsExecuted() ?
                "Starting execution of method: "+methodName+" with parameters: "+ Arrays.stream(method).map(o->o.toString()+" ").toString()
                : "Finishing execution of method: "+ methodName + (super.getResult()==null ? "":" with result: "+super.getResult());
    }

    @Override
    public String warn() {
        return super.getIsExecuted() ? "Warning before execution":"Warning after execution";
    }

    @Override
    public String error(Throwable throwable) {
        return "ERROR in "+super.getJoinPoint().getSignature().toString()+" Exception details: "+throwable.toString();
    }
}
