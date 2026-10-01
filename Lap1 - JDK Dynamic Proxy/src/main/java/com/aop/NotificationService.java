package com.aop;

public interface NotificationService {

    void sendEmail(String user, String message);

    void sendSms(String user, String message);

}
