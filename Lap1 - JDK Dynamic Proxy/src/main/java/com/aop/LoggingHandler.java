package com.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class LoggingHandler implements InvocationHandler {

    private final Object target;

    public LoggingHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        long start = System.currentTimeMillis();

        System.out.println("LOG: calling " + method.getName() + " args=" + Arrays.toString(args));
        try {

            Object result = method.invoke(target, args); // Must use target

            String returned = method.getReturnType() == void.class ? "void" : String.valueOf(result);

            System.out.println("LOG: finished " + method.getName() + " returned=" + returned);
            return result;
        } catch (InvocationTargetException e) {
            System.out.println("!! LOG: " + method.getName() + " threw " + e.getCause());
            throw e.getCause();
        } finally {
            System.out.println("   TIMER: " + method.getName() + " took " + (System.currentTimeMillis() - start) + " ms");
        }
    }
}
