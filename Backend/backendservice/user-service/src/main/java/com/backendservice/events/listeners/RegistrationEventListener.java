package com.backendservice.events.listeners;

import com.backendservice.events.RegistrationEvent;
import com.backendservice.models.UserCollection;
import com.backendservice.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class RegistrationEventListener implements ApplicationListener<RegistrationEvent> {

    @Autowired
    private UserService userService;
    // makeshift logger for time being
    private final Logger logger = LoggerFactory.getLogger(RegistrationEventListener.class);

    @Override
    public void onApplicationEvent(RegistrationEvent event){
        UserCollection user = event.getUser();
        String token = UUID.randomUUID().toString();

        userService.saveVerificationTokenForUser(user, token);

        String url = event.getApplicationUrl() + "/user/verifyRegistration?token=" + token;
        logger.info("Click the url -> {}", url);
    }
}
