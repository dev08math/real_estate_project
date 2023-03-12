package com.backendservice.services;

import com.backendservice.dto.*;
import com.backendservice.models.Roles;
import com.backendservice.models.TokenCollection;
import com.backendservice.models.UserCollection;
import com.backendservice.utils.ERole;
import com.backendservice.utils.VerificationToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;
@Service
public class UserServiceImpl implements UserService {
    private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private CloudinaryService cloudinaryService;
    @Autowired
    private UsersAssetService usersAssetService;
    @Autowired
    private MongoTemplate mt;

    @Override
    public UserCollection registerNewUser(UserRegistrationRequest userRegistrationRequest) {
        UserCollection user = new UserCollection();

        user.setUserName(userRegistrationRequest.getUserName());
        user.setEmail(userRegistrationRequest.getEmail());
        user.setPassword(userRegistrationRequest.getPassword()); // Encoded Password will arrive
        user.setPhoneNumber(userRegistrationRequest.getPhoneNumber());

        user.setDisplayLink(cloudinaryService.uploadToCloudinary(usersAssetService.getDpFile(), userRegistrationRequest.getUserName(), "profile", "default_dp"));

        List<String> rolesList;
        rolesList = userRegistrationRequest.getRoles();
        Set<Roles> roles = new HashSet<>();
        if(userRegistrationRequest.getRoles() != null){
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

        return mt.findAndModify(query,
                updateDefinition,
                findAndModifyOptions,
                TokenCollection.class,
                "TokenCollection");
    }

    @Override
    public String validateVerificationToken(String token) {
        Query query = new Query().addCriteria(Criteria.where("verificationToken.token").is(token));
        TokenCollection tokenObj = mt.findOne(query, TokenCollection.class,"TokenCollection");
        if (tokenObj == null)
            return "Invalid";

        Calendar cal = Calendar.getInstance();
        if (tokenObj.getVerificationToken().getExpirationTime().getTime() - cal.getTime().getTime() < 0) {
            mt.remove(tokenObj);
            return "Expired";
        }

        UserCollection user = mt.findById(tokenObj.getUserId(), UserCollection.class, "UserCollection");
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
        UserCollection user = mt.findOne(query, UserCollection.class, "UserCollection");
        if(user == null) throw new IllegalArgumentException("Can't find the username");
        return user.getUserName();
    }
    @Override
    public ChatUserDetailsResponse chatGetUserDetails(String userName) throws IllegalArgumentException {
        Query query = new Query().addCriteria(Criteria.where("userName").is(userName));
        UserCollection user = mt.findOne(query, UserCollection.class, "UserCollection");
        ChatUserDetailsResponse chatUserDetailsResponse = new ChatUserDetailsResponse();
        if(user == null) throw new IllegalArgumentException("Can't find the username");
        chatUserDetailsResponse.setDisplayLink(user.getDisplayLink());
        chatUserDetailsResponse.setUserName(user.getUserName());
        return chatUserDetailsResponse;
    }

    @Override
    public UserDetailsResponse getUserDetailsByUsername(String userName) throws IllegalArgumentException {
        Query query = new Query().addCriteria(Criteria.where("userName").is(userName));
        UserCollection user = mt.findOne(query, UserCollection.class, "UserCollection");
        UserDetailsResponse userDetailsResponse = new UserDetailsResponse();
        if(user == null) throw new IllegalArgumentException();
        userDetailsResponse.setId(user.getUserId());
        userDetailsResponse.setDisplayLink(user.getDisplayLink());
        userDetailsResponse.setUserName(userName);
        userDetailsResponse.setPassword(user.getPassword());
        userDetailsResponse.setEmail(user.getEmail());
        userDetailsResponse.setMyProperties(user.getMyProperties());
        List<String> roles = user.getRoles().stream().map(role -> role.getERole().toString()).toList();
        userDetailsResponse.setAuthorities(roles);
        return userDetailsResponse;
    }

    @Override
    public OwnerDetailsResponse getUserDetailsByID(String userID) {
        Query query = new Query().addCriteria(Criteria.where("userId").is(userID));
        UserCollection user = mt.findOne(query, UserCollection.class, "UserCollection");
        if(user == null) throw new IllegalArgumentException();
        OwnerDetailsResponse ownerDetailsResponse = new OwnerDetailsResponse();
        ownerDetailsResponse.setOwnerEmail(user.getEmail());
        ownerDetailsResponse.setOwnerName(user.getUserName());
        ownerDetailsResponse.setOwnerPhoneNumber(user.getPhoneNumber().toString());
        return ownerDetailsResponse;
    }

    @Override
    public String addNewProperty(PropertyDetails propertyDetails) {
        Query query = new Query().addCriteria(Criteria.where("userId").is(propertyDetails.getUserId()));
        UserCollection user = mt.findOne(query, UserCollection.class, "UserCollection");
        if(user == null) throw new IllegalArgumentException();
        List<String> propertyList = user.getMyProperties();
        propertyList.add(propertyDetails.getPropId());
        user.setMyProperties(propertyList);
        return "Success";
    }

    @Override
    public UserCollection getUserByUserName(String userName) {
        Query query = new Query().addCriteria(Criteria.where("userName").is(userName));
        UserCollection user = mt.findOne(query, UserCollection.class, "UserCollection");
        if(user == null) throw new IllegalArgumentException();
        return user;
    }

}
