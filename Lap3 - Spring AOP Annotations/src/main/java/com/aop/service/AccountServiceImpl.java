package com.aop.service;

import com.aop.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
public class AccountServiceImpl implements AccountService {

    @Override
    public void withdraw(String accountId, double amount) {
        System.out.println("Withdrawing " + amount + " from " + accountId);
    }


    @Override
    public void deposit(String accountId, double amount) {
        System.out.println("Depositing " + amount + " into " + accountId);
    }


    @Override
    public String retrieveBalance(String accountId) {
        System.out.println("Returning String Balance");
        return "1000EG";
    }


    @Override
    public void willThrowException() {
        System.out.println(" -> Throwing Exception");
    }
}
