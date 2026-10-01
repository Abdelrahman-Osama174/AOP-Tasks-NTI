package com.aop.config;

import com.aop.service.InventoryService;
import com.aop.service.InventoryServiceImpl;
import com.aop.advice.*;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/** Task 6: the same proxy, but as a container-managed bean. */
@Configuration
public class AppConfig {

    // ---- the real object --------------------------------------------------
    @Bean
    public InventoryServiceImpl inventoryServiceTarget() {
        return new InventoryServiceImpl();
    }


    // ---- the four advices -------------------------------------------------
    @Bean
    public LoggingBeforeAdvice loggingBeforeAdvice() {
        return new LoggingBeforeAdvice();
    }

    @Bean
    public LoggingAfterReturningAdvice stockResultAdvice() {
        return new LoggingAfterReturningAdvice();
    }

    @Bean
    public LoggingThrowsAdvice reserveStockThrowsAdvice() {
        return new LoggingThrowsAdvice();
    }

    @Bean
    public TimingInterceptor timingInterceptor() {
        return new TimingInterceptor();
    }


    // ---- the proxy (this is the bean other classes receive) ----------------
    @Bean
    public ProxyFactoryBean inventoryService() {
        ProxyFactoryBean factory = new ProxyFactoryBean();

        factory.setTarget(inventoryServiceTarget());
        factory.setInterfaces(InventoryService.class);

        factory.setInterceptorNames(
                "loggingBeforeAdvice",
                "stockResultAdvice",
                "reserveStockThrowsAdvice",
                "timingInterceptor");
        return factory;
    }

}
