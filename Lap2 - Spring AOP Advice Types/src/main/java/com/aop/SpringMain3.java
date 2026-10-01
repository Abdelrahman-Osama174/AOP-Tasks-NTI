package com.aop;

import com.aop.config.AppConfig;
import com.aop.service.InventoryService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * - Aop Using Spring and Spring Container (AppConfig)
 */
public class SpringMain3 {

    static void main() {

        System.out.println("\n========================== Using Spring Config: ============================");

        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        // ProxyFactory in AppConfig
        InventoryService service = context.getBean("inventoryService", InventoryService.class);

        System.out.println("Proxy class: " + service.getClass().getName() + "\n");

        /// direct call on the container bean
        System.out.println("1) checkStock:");
        service.checkStock("SKU-2");

        System.out.println("\n2) reverse(101) exception:");
        try {
            service.reserveStock("SKU-1", 101);
        } catch (IllegalStateException e) {
            System.out.println("    -→ caught: " + e.getMessage());
        }

    }
}
