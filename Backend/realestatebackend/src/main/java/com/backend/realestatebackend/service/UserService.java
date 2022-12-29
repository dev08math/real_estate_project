package com.backend.realestatebackend.service;

import com.backend.realestatebackend.collections.TokenCollection;
import com.backend.realestatebackend.collections.UserCollection;
import com.backend.realestatebackend.models.UserRegisterModel;

public interface UserService {

    UserCollection registerNewUser(UserRegisterModel userModel);

    void saveVerificationTokenForUser(UserCollection user, String token);

    TokenCollection generateNewToken(String token);

    String validateVerficationToken(String token);

    String getUsernameByEmail(String username) throws IllegalArgumentException;
    
}
