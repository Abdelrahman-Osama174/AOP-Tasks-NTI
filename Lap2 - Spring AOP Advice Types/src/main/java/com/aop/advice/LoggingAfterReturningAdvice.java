package com.aop.advice;

import java.lang.reflect.Method;

public class LoggingAfterReturningAdvice implements org.springframework.aop.AfterReturningAdvice {

    @Override
    public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
        if ("checkStock".equals(method.getName())) {
            System.out.println("[AFTER-RETURNING] " + method.getName() + " returned " + returnValue);
        }
    }
}
