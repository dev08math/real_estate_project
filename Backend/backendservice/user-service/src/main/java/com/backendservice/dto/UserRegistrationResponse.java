package com.backendservice.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
public class UserRegistrationResponse {
    private String id;
    private String userName;
    private String email;
    private String displayLink;
    private List<String> roles;
}
