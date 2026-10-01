package com.aop;

import java.lang.reflect.Proxy;

public class Main {
    public static void main(String[] args) {

        NotificationService notificationService = new NotificationServiceImpl();

        NotificationService proxy = (NotificationService) Proxy.newProxyInstance(
                NotificationService.class.getClassLoader(),
                new Class[]{NotificationService.class},
                new LoggingHandler(notificationService)
        );

        System.out.println("\n====================== JDK Proxy ========================");
        System.out.println("Proxy class: " + proxy.getClass().getName());
        System.out.println();

        proxy.sendEmail("Abdelrahman", "Must you lear Proxy Design Pattern");
        System.out.println();
        proxy.sendSms("Mostafa", "Your code is 1234");
    }
}