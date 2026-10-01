package com.aop.advice;

import org.springframework.aop.ThrowsAdvice;

import java.lang.reflect.Method;

public class LoggingThrowsAdvice implements ThrowsAdvice {

    public void afterThrowing(Method method, Object[] args, Object target, Exception ex) {
        if ("reserveStock".equals(method.getName())) {
            System.out.println("[AFTER-THROWING] " + method.getName() +
                    " threw " + ex.getClass().getSimpleName() + ": " + ex.getMessage());
        }
    }
}
