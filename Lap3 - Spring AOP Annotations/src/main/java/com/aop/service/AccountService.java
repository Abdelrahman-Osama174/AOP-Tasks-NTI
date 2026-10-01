package com.aop.service;

public interface AccountService {
    void withdraw(String accountId, double amount);
    void deposit(String accountId, double amount);
    String retrieveBalance(String accountId);
    void willThrowException();
}
