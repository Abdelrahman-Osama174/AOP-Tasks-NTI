package com.aop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class LoggingAspect {

    /**
     * Named pointcut, written once and reused by all four advices.
     */
    @Pointcut("execution(* com.aop.service.AccountService.*(..))")
    public void productService() {
    }


    @Before("productService()")
    public void logBefore(JoinPoint jp) {
        System.out.println("[BEFORE] " + jp.getSignature().getName() + " args=" + Arrays.toString(jp.getArgs()));
    }


    @AfterReturning(pointcut = "productService()", returning = "result")
    public void logSuccess(JoinPoint jp, Object result) {
        System.out.println("[AFTER-RETURNING] " + jp.getSignature().getName() + " returned " + result);
    }


    @AfterThrowing(pointcut = "productService()", throwing = "ex")
    public void logFailure(JoinPoint jp, Exception ex) {
        System.out.println("[AFTER-THROWING] " + jp.getSignature().getName() + " threw " + ex.getClass().getSimpleName() + ": " + ex.getMessage());
    }


    @After("productService()")
    public void logAfter(JoinPoint jp) {
        System.out.println("[AFTER (finally)] " + jp.getSignature().getName() + " executed");
    }
}