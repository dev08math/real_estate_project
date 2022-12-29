package com.backend.realestatebackend.models;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserRegisterModel {
    private String name;
    private Long phoneNumber;
    private String email;
    private String password;
    private List<String> roles;
}
