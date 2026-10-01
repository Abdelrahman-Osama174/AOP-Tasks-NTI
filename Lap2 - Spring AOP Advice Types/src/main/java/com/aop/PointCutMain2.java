package com.aop;

import com.aop.advice.LoggingBeforeAdvice;
import com.aop.advice.LoggingAfterReturningAdvice;
import com.aop.advice.TimingInterceptor;
import com.aop.service.InventoryService;
import com.aop.service.InventoryServiceImpl;

import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;

/**
 * - Aop Using Proxy Factory not using Spring and Spring Container (AppConfig)
 * - using Pointcuts to apply advice on specific methods
 */
public class PointCutMain2 {

    static void main(String[] args) {

        System.out.println("\n========================== Proxy Factory Without Spring: ============================");
        System.out.println("======================== (Using Pointcuts) ==========================");

        NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
        pointcut.setMappedName("reserveStock");              // only this method

        // Advice + Pointcut = Advisor
        Advisor advisor = new DefaultPointcutAdvisor(pointcut, new TimingInterceptor());

        ProxyFactory factory = new ProxyFactory(new InventoryServiceImpl());
        factory.addAdvice(new LoggingBeforeAdvice());            // no pointcut => all methods
        factory.addAdvisor(advisor);
        factory.addAdvice(new LoggingAfterReturningAdvice());   // no pointcut => all methods


        InventoryService proxy = (InventoryService) factory.getProxy();
        System.out.println("Proxy class: " + proxy.getClass().getName() + "\n");

        System.out.println("1) checkStock:");
        proxy.checkStock("SKU-1");

        System.out.println("\n2) reserveStock:");
        proxy.reserveStock("SKU-1", 5);
    }
}
