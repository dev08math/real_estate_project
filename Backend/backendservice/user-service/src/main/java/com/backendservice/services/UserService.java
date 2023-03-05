package com.backendservice.services;

import com.backendservice.dto.ChatUserDetailsResponse;
import com.backendservice.dto.OwnerDetailsResponse;
import com.backendservice.dto.UserDetailsResponse;
import com.backendservice.dto.UserRegistrationRequest;
import com.backendservice.models.TokenCollection;
import com.backendservice.models.UserCollection;

public interface UserService {

    UserCollection registerNewUser(UserRegistrationRequest userRegistrationRequest);

    void saveVerificationTokenForUser(UserCollection user, String token);
    TokenCollection generateNewToken(String token);
    String validateVerificationToken(String token);
    String getUsernameByEmail(String username) throws IllegalArgumentException;
    ChatUserDetailsResponse chatGetUserDetails(String name);
    UserDetailsResponse getUserDetailsByUsername(String userName);
    OwnerDetailsResponse getUserDetailsByID(String userID);
}
