package com.backendservice.dto.UserDTOs;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDetailsRequest {
    private String userName;
    private String password;
}
