package com.aop.service;

import java.util.Map;

public class InventoryServiceImpl implements InventoryService {

    private static final int MAX_RESERVATION = 100;

    private final Map<String, Integer> stock = Map.of("SKU-1", 42, "SKU-2", 7);

    @Override
    public int checkStock(String sku) {
        return stock.getOrDefault(sku, 0);
    }

    @Override
    public void reserveStock(String sku, int qty) {
        if (qty > MAX_RESERVATION) {
            throw new IllegalStateException("Cannot reserve " + qty + " units of " + sku + " (max " + MAX_RESERVATION + ")");
        }
        System.out.println("You reserved " + qty + " x " + sku);
    }
}
