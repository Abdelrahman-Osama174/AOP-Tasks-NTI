package com.aop.service;

public interface InventoryService {

    int checkStock(String sku);

    void reserveStock(String sku, int qty);
}
