package com.github.kacperroman.taskmanager.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.AfterReturning;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);
    @Before("execution(* com.github.kacperroman.taskmanager.controller.*.*(..))")
    public void logBefore(JoinPoint joinPoint) {

        logger.info("Before: {} ", joinPoint.getSignature().getName());
    }

    @AfterReturning(pointcut = "execution(* com.github.kacperroman.taskmanager.controller.*.*(..))", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        String method = joinPoint.getSignature().getName();
        if (result != null) {
            logger.info("AfterReturning: {} returned -> {}", method, result);
        } else {
            logger.info("AfterReturning: {} returned null or void", method);
        }
    }

}
