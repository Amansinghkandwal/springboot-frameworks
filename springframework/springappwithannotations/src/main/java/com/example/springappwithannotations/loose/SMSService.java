package com.example.springappwithannotations.loose;

import org.springframework.stereotype.Component;

@Component
public class SMSService implements NotificationService{

    @Override
    public void send(String message) {
        System.out.println("SMS : "+message);
    }
}
