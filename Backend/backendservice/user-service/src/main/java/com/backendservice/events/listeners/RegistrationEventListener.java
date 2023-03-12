package com.backendservice.events.listeners;

import com.backendservice.events.RegistrationEvent;
import com.backendservice.models.UserCollection;
import com.backendservice.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

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

        String url = event.getApplicationUrl() + "/api/public/user/verifyRegistration?token=" + token;
        logger.info("Click the url -> {}", url);

        MultiValueMap<String, String> emailBody = new LinkedMultiValueMap<>();

        emailBody.add("subject", "User Verification");
        emailBody.add("recipient", user.getEmail());
        emailBody.add("body", String.format("Click the url -> %s to verify.", url));

        WebClient webClient = WebClient.create("http://email-service/api/email");
        String response  = webClient.post()
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(BodyInserters.fromFormData(emailBody))
                .retrieve()
                .onStatus(HttpStatus.NOT_FOUND::equals, clientResponse -> Mono.empty())
                .bodyToMono(String.class)
                .block();

        if(response == null) {
            logger.error("Unable to send the email");
            return;
        }
        logger.info(response);
    }
}
