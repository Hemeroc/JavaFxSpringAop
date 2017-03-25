package com.example;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    @Before("execution(* com.example..*(..))")
    public void logMethodCall(JoinPoint joinPoint) {
        LoggerFactory.getLogger(
                joinPoint.getTarget().getClass()).info("called: {}({})",
                joinPoint.getSignature().getName(),
                joinPoint.getArgs());
    }
}
