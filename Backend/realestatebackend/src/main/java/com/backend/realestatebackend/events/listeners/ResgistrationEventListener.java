package com.backend.realestatebackend.events.listeners;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.backend.realestatebackend.collections.UserCollection;
import com.backend.realestatebackend.events.RegistrationEvent;
import com.backend.realestatebackend.service.UserService;

@Component
public class ResgistrationEventListener implements ApplicationListener<RegistrationEvent>{
    
    @Autowired
    private UserService userService;

    // makeshift logger for time being 
    private Logger logger = LoggerFactory.getLogger(ResgistrationEventListener.class);

    @Override
    public void onApplicationEvent(RegistrationEvent event){
        UserCollection user = event.getUser();
        String token = UUID.randomUUID().toString();

        userService.saveVerificationTokenForUser(user, token);

        String url = event.getApplicationUrl() + "/user/verifyregistration?token=" + token;
        logger.info("Click the url -> {}", url);
    }
}
