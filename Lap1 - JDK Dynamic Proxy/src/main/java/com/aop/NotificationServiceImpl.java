package com.aop;

public class NotificationServiceImpl implements NotificationService {

    @Override
    public void sendEmail(String user, String message) {
        System.out.println("Email sent to " + user + ": " + message);
    }

    @Override
    public void sendSms(String user, String message) {
        System.out.println("SMS sent to " + user + ": " + message);
    }
}
