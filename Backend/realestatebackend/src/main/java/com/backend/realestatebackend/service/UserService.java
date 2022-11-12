package com.backend.realestatebackend.service;

import com.backend.realestatebackend.collections.TokenCollection;
import com.backend.realestatebackend.collections.UserCollection;
import com.backend.realestatebackend.models.UserModel;

public interface UserService {

    UserCollection registerNewUser(UserModel userModel);

    void saveVerificationTokenForUser(UserCollection user, String token);

    TokenCollection generateNewToken(String token);

    String validateVerficationToken(String token);
    
}
