package com.backendservice.dto.UserDTOs;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class UserDetailsResponse {
    private String userName;
    private String email;
    private String displayLink;
    private String id;
    private List<String> authorities;
    private String password;
}
