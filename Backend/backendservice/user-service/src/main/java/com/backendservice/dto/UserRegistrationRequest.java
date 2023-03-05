package com.backendservice.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserRegistrationRequest {
    private String userName;
    private Long phoneNumber;
    private String email;
    private String password;
    private List<String> roles;
}
