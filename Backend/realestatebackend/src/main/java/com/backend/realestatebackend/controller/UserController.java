package com.backend.realestatebackend.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backend.realestatebackend.collections.TokenCollection;
import com.backend.realestatebackend.collections.UserCollection;
import com.backend.realestatebackend.events.RegistrationEvent;
import com.backend.realestatebackend.models.UserModel;
import com.backend.realestatebackend.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private ApplicationEventPublisher publisher;

    private static String generateUrl(HttpServletRequest request) {
        return "http://" +
                request.getServerName() +
                ":" +
                request.getServerPort() +
                request.getContextPath();
    }

    @PostMapping("/register")
    public UserCollection generateNewUser(@RequestBody UserModel userModel, final HttpServletRequest request) {
        UserCollection user = userService.registerNewUser(userModel);
        publisher.publishEvent(new RegistrationEvent(user, generateUrl(request)));
        return user;
    }

    @GetMapping("/verifyregistration")
    public String verifyRegistration(@RequestParam("token") String token) {
        String result = userService.validateVerficationToken(token);
        if (result.equalsIgnoreCase("valid"))
            return "Token Validated";
        return result;
    };

    @GetMapping("/resendverificationtoken")
    public String resendVerificationToken(@RequestParam("token") String token, final HttpServletRequest request) {
        TokenCollection tokenCollection = userService.generateNewToken(token);
        if (tokenCollection == null)
            return "Invalid Token";
        return generateUrl(request) + "user/verifyregistration?token="
                + tokenCollection.getVerificationToken().getToken();
    }
}
