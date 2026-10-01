package com.aop.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Aspect
@Component
public class CachingAspect {

    private final Map<String, Object> cache = new ConcurrentHashMap<>();

    @Around("@annotation(com.aop.annotation.Cacheable)")
    public Object cache(ProceedingJoinPoint pjp) throws Throwable {

        String key = pjp.getArgs()[0].toString();

        if (cache.containsKey(key)) {
            System.out.println("CACHE HIT: " + key);
            return cache.get(key);
        }

        System.out.println("CACHE MISS: " + key);
        Object result = pjp.proceed();   // run the real method
        cache.put(key, result);
        return result;
    }
}
