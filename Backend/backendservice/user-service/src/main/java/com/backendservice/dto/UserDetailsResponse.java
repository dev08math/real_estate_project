package com.backendservice.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
public class UserDetailsResponse {
    private String userName;
    private String password;
    private String email;
    private String id;
    private String displayLink;
    private List<String> authorities;
}
