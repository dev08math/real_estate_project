package com.backend.realestatebackend.models;

import lombok.Data;

@Data
public class UserLoginModel {
    private String email;
    private String password;
}
