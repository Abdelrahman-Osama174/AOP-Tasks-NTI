package com.aop.advice;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;


public class TimingInterceptor implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {

        long start = System.currentTimeMillis();

        System.out.println("[AROUND-START]   " + invocation.getMethod().getName());

        try {
            return invocation.proceed();  // without this the real method never runs
        } finally {
            System.out.println("[AROUND-FINALLY] " + invocation.getMethod().getName() + " took " + (System.currentTimeMillis() - start) + " ms");
        }
    }
}
