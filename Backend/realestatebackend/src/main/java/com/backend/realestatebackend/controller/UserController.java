package com.backend.realestatebackend.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cloudinary.Cloudinary;

@RestController
@RequestMapping("/user")
public class UserController {
    
    Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private Cloudinary cloudinary;

    
}
