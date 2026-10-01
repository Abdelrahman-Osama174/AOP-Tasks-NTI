package com.aop;


import com.aop.advice.*;
import com.aop.service.InventoryService;
import com.aop.service.InventoryServiceImpl;
import org.springframework.aop.framework.ProxyFactory;

/**
 * - Aop Using Proxy Factory not using Spring and Spring Container (AppConfig)
 * - Not using Pointcuts  → Every advice applies to every method
 */
public class ProxyFactoryMain1 {
    static void main() {

        System.out.println("\n========================== Proxy Factory Without Spring: ============================");
        System.out.println("======================== (Not Using Pointcuts) ==========================");

        ProxyFactory factory = new ProxyFactory(new InventoryServiceImpl());

        factory.addAdvice(new LoggingBeforeAdvice());
        factory.addAdvice(new LoggingAfterReturningAdvice());
        factory.addAdvice(new LoggingThrowsAdvice());
        factory.addAdvice(new TimingInterceptor());

        InventoryService proxy = (InventoryService) factory.getProxy();
        System.out.println("Proxy class: " + proxy.getClass().getName() + "\n");

        System.out.println("1) checkStock:");
        proxy.checkStock("SKU-1");

        System.out.println("\n2) reserveStock(10):");
        proxy.reserveStock("SKU-1", 10);

        System.out.println("\n3) reserveStock(500):");
        try {
            proxy.reserveStock("SKU-1", 500);
        } catch (IllegalStateException e) {
            System.out.println("    -→ caught: " + e.getMessage());
        }
    }
}
