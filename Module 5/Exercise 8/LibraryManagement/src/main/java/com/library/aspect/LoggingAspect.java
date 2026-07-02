package com.library.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class LoggingAspect {

    @Before("execution(* com.library.service.BookService.manageBooks(..))")
    public void logBefore(JoinPoint joinPoint) {
        System.out.println("[AOP BEFORE] Aspect logging: Starting execution of " + joinPoint.getSignature().getName());
    }

    @After("execution(* com.library.service.BookService.manageBooks(..))")
    public void logAfter(JoinPoint joinPoint) {
        System.out.println("[AOP AFTER] Aspect logging: Finished execution of " + joinPoint.getSignature().getName());
    }
}
