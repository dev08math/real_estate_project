package com.backend.realestatebackend.models;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserModel {
    private String name;
    private Long phoneNumber;
    private String email;
    private String password;
}
