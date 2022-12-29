package com.backend.realestatebackend.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.backend.realestatebackend.collections.Roles;
import com.backend.realestatebackend.collections.TokenCollection;
import com.backend.realestatebackend.collections.UserCollection;
import com.backend.realestatebackend.fields.TokenFields.VerificationToken;
import com.backend.realestatebackend.models.ERole;
import com.backend.realestatebackend.models.UserRegisterModel;

@Service
public class UserServiceImpl implements UserService {

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private CloudinaryService cloudinaryService;

    @Autowired
    private AssetProvider assetProvider;

    @Autowired
    private MongoTemplate mt;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserCollection registerNewUser(UserRegisterModel userModel) {
        UserCollection user = new UserCollection();

        user.setName(userModel.getName());
        user.setEmail(userModel.getEmail());
        user.setPassword(passwordEncoder.encode(userModel.getPassword()));
        user.setPhoneNumber(userModel.getPhoneNumber());

        user.setDp(cloudinaryService.uploadToCloudinary(assetProvider.getDpFile(), userModel.getName(), "profile", "default_dp"));

        List<String> rolesList = new ArrayList<String>();
        rolesList = userModel.getRoles();
        Set<Roles> roles = new HashSet<>();
        if(userModel.getRoles() != null){
            for(String role : rolesList){
                Roles r = new Roles();
                r.setERole(ERole.valueOf(role));
                mt.save(r);
                roles.add(r);
            }
        }
        else{
            Roles r = new Roles();
            r.setERole(ERole.valueOf("ROLE_USER"));
            mt.save(r);
            roles.add(r);
        }
        user.setRoles(roles); 

        Date currentDate = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy");
        String joiningDate = formatter.format(currentDate);
        user.setJoiningDate(joiningDate);

        mt.save(user);
        return user;
    }

    @Override
    public void saveVerificationTokenForUser(UserCollection user, String token) {

        VerificationToken verificationToken = new VerificationToken(token);
        TokenCollection tokenCollection = new TokenCollection();

        tokenCollection.setUserId(user.getUserId());
        tokenCollection.setVerificationToken(verificationToken);

        mt.save(tokenCollection);
    }

    @Override
    public TokenCollection generateNewToken(String token) {
        Query query = new Query();
        query.addCriteria(Criteria.where("verificationToken.token").is(token));

        Update updateDefinition = new Update();
        updateDefinition.set("verificationToken.token", UUID.randomUUID().toString());

        FindAndModifyOptions findAndModifyOptions = new FindAndModifyOptions();
        findAndModifyOptions.returnNew(true).upsert(false);

        return mt.findAndModify(query, updateDefinition, findAndModifyOptions, TokenCollection.class);
    }

    @Override
    public String validateVerficationToken(String token) {
        Query query = new Query().addCriteria(Criteria.where("verificationToken.token").is(token));
        TokenCollection tokenObj = mt.findOne(query, TokenCollection.class);
        if (tokenObj == null)
            return "Invalid";

        Calendar cal = Calendar.getInstance();
        if (tokenObj.getVerificationToken().getExpirationTime().getTime() - cal.getTime().getTime() < 0) {
            mt.remove(tokenObj);
            return "Expired";
        }

        UserCollection user = mt.findById(tokenObj.getUserId(), UserCollection.class);
        if (user == null)
            return "Cannot find the user";
        user.setEnabled(true);
        mt.save(user);
        logger.info(user.toString());
        return "Valid";
    }

    @Override
    public String getUsernameByEmail(String email) throws IllegalArgumentException {
        Query query = new Query().addCriteria(Criteria.where("email").is(email));
        UserCollection user = mt.findOne(query, UserCollection.class);
        if(user == null) throw new IllegalArgumentException("Can't find the username");
        return user.getName();
    }

}
