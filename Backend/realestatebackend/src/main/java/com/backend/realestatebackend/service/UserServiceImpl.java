package com.backend.realestatebackend.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.realestatebackend.collections.UserCollection;
import com.backend.realestatebackend.dao.UserRepository;
import com.backend.realestatebackend.models.UserModel;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private CloudinaryService cloudinaryService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AssetProvider assetProvider;

    @Override
    public UserCollection registerNewUser(UserModel userModel) {
        UserCollection user = new UserCollection();
        
        user.setName(userModel.getName());
        user.setEmail(userModel.getEmail());
        user.setPassword(userModel.getPassword());
        user.setPhoneNumber(userModel.getPhoneNumber());

        user.setDp(cloudinaryService.uploadToCloudinary(assetProvider.getDpFile(), userModel.getName(), "default_dp"));
        
        Date currentDate = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy");
        String joiningDate = formatter.format(currentDate);
        user.setJoiningDate(joiningDate);

        userRepository.save(user);
        return user;
    }
    
}
