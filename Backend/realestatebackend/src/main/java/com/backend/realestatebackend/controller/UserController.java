package com.backend.realestatebackend.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.realestatebackend.collections.UserCollection;
import com.backend.realestatebackend.models.UserModel;
import com.backend.realestatebackend.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
    
    Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @GetMapping("/newUser")
    public String  generateNewUser(@RequestBody UserModel userModel){
        UserCollection user = userService.registerNewUser(userModel);
        return user.getId();
    }
}
