package com.backendservice.dto.UserDTOs;

import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
public class UserRegRequest {
    String userName;
    String password;
    String email;
    String phoneNumber;
}
