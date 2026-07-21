package com.cognizant.springlearn.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingAspect.class);

    @Before("execution(* com.cognizant.springlearn.service.*.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        LOGGER.info("START : {}", joinPoint.getSignature().getName());
    }

    @After("execution(* com.cognizant.springlearn.service.*.*(..))")
    public void logAfter(JoinPoint joinPoint) {
        LOGGER.info("END : {}", joinPoint.getSignature().getName());
    }
}
