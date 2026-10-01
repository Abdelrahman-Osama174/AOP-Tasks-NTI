package com.aop;


import com.aop.config.AppConfig;
import com.aop.selfinvocation.OrderService;
import com.aop.service.AccountService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        AccountService accountService = context.getBean(AccountService.class);

        System.out.println("\n========================= 5 advice types + Around =============================");
        System.out.println("1) deposit(acc-1, 150.5):");
        accountService.deposit("acc-1", 150.5);

        System.out.println("\n2) withdraw(acc-1, 100):");
        accountService.withdraw("acc-1", 100.0);

        System.out.println("\n3) retrieveBalance(acc-1):");
        accountService.retrieveBalance("acc-1");

        System.out.println("\n4) withThrowException():");
        accountService.willThrowException();



        System.out.println("\n\n====================================================================");
        System.out.println("========================= Test Caching =============================");
        OrderService orderService = context.getBean(OrderService.class);
        System.out.println("5) getOrder(1):");
        orderService.getOrder("1");

        System.out.println("\n6) getOrder(1):");
        orderService.getOrder("1");



        System.out.println("\n\n====================================================================");
        System.out.println("===================== Test Self Invocation =========================");

        System.out.println("7) processOrder(1):");
        orderService.processOrder("1");

    }
}
