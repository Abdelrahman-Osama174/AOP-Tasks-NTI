package com.aop.selfinvocation;

import com.aop.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Cacheable
    public String getOrder(String id) throws InterruptedException {
        Thread.sleep(300); // To Test Caching
        System.out.println("Fetching order " + id + " from the database...");
        return "Order[" + id + "]";
    }

    public void processOrder(String id) throws InterruptedException {
        // the Spring proxy, so @Cacheable will NOT fire here.
        String order = getOrder(id);
        System.out.println("Processing " + order);
    }
}
