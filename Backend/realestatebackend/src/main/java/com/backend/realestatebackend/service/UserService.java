package com.backend.realestatebackend.service;

import com.backend.realestatebackend.collections.UserCollection;
import com.backend.realestatebackend.models.UserModel;

public interface UserService {

    UserCollection registerNewUser(UserModel userModel);
    
}
