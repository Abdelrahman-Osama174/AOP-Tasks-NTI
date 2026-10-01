package com.aop.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class AroundAspect {

    @Around("execution(* com.aop.service.AccountService.*(..))")
    public Object aroundAll(ProceedingJoinPoint pjp) throws Throwable {
        String name = pjp.getSignature().getName();

        System.out.println("[AROUND-BEFORE] " + name + " args=" + Arrays.toString(pjp.getArgs()));

        Object result = pjp.proceed();    // without this the real method never runs

        System.out.println("[AROUND-AFTER] " + name + " returned " + result);
        return result;

    }
}
