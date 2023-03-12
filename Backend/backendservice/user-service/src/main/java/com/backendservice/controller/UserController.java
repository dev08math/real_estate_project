package com.backendservice.controller;

import com.backendservice.dto.*;
import com.backendservice.events.RegistrationEvent;
import com.backendservice.models.TokenCollection;
import com.backendservice.models.UserCollection;
import com.backendservice.services.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;
    @Autowired
    private ApplicationEventPublisher publisher;

    private static String generateUrl(HttpServletRequest request) {
        return "http://" +
                request.getServerName() +
                ":" +
                request.getServerPort() +
                request.getContextPath();
    }

    @GetMapping("/userDetails")
    public ResponseEntity<?> authenticateUser(@RequestParam("userName") String userName)  {
        UserDetailsResponse userDetailsResponse;
        try{
            userDetailsResponse = userService.getUserDetailsByUsername(userName);
        } catch (Exception e) {
            logger.error(e.toString());
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body("Invalid userName / password");
        }
        return ResponseEntity.ok(userDetailsResponse);
    }

    @GetMapping("/userDetailsByID")
    public ResponseEntity<?> getDetailsByID(@RequestParam("userID") String userID){
        OwnerDetailsResponse ownerDetailsResponse;
        try{
            ownerDetailsResponse = userService.getUserDetailsByID(userID);
        } catch (Exception e) {
            logger.error(e.toString());
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body("Invalid userID");
        }
        return ResponseEntity.ok(ownerDetailsResponse);
    }

    @PostMapping("/registerUser")
    public ResponseEntity<?> generateNewUser(@RequestBody UserRegistrationRequest userRegistrationRequest, final HttpServletRequest request) {

        UserCollection user;
        try {
            user = userService.registerNewUser(userRegistrationRequest);
        }catch (Exception e){
            logger.error(e.toString());
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(e.toString());
        }

        publisher.publishEvent(new RegistrationEvent(user, generateUrl(request)));
        UserRegistrationResponse userRegistrationResponse = new UserRegistrationResponse();
        userRegistrationResponse.setUserName(user.getUserName());
        userRegistrationResponse.setId(user.getUserId());
        userRegistrationResponse.setEmail(user.getEmail());
        userRegistrationResponse.setDisplayLink(user.getDisplayLink());
        List<String> roles = user.getRoles().stream().map(role -> role.getERole().toString()).toList();
        userRegistrationResponse.setRoles(roles);
        return ResponseEntity.ok(userRegistrationResponse);
    }

    @PostMapping("/verifyRegistration")
    public String verifyRegistration(@RequestParam("token") String token) {
        String result = userService.validateVerificationToken(token);
        if (result.equalsIgnoreCase("valid"))
            return "Token Validated";
        return result;
    }

    @PostMapping("/resendVerificationToken")
    public String resendVerificationToken(@RequestParam("token") String token, final HttpServletRequest request) {
        TokenCollection tokenCollection = userService.generateNewToken(token);
        if (tokenCollection == null)
            return "Invalid Token";
        return generateUrl(request) + "user/verifyRegistration?token="
                + tokenCollection.getVerificationToken().getToken();
    }

    @GetMapping("/getChatUserDetails")
    public ChatUserDetailsResponse getUserDetailsForChat(@RequestParam("userName") String name){
        return userService.chatGetUserDetails(name);
    }

    @PostMapping("/addNewProperty")
    public ResponseEntity<?> addNewProperty(@RequestBody PropertyDetails propertyDetails){
        String result;
        try{
            result = userService.addNewProperty(propertyDetails);
        }
        catch(Exception e){
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body("Error in adding new property");
        }
        return ResponseEntity.ok(result);
    }

    private UserCollection applyPatchToUser(
            JsonPatch patch, UserCollection targetUser) throws JsonPatchException, JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode patched = patch.apply(objectMapper.convertValue(targetUser, JsonNode.class));
        return objectMapper.treeToValue(patched, UserCollection.class);
    }
    @PatchMapping("/updateUserDetails")
    public ResponseEntity<?> updateDetails(@RequestBody JsonPatch patch, @RequestParam("userName") String userName){
        try{
            UserCollection user = userService.getUserByUserName(userName);
            UserCollection updatedUser = applyPatchToUser(patch, user);
        } catch (JsonPatchException | JsonProcessingException e) {
            logger.info(String.format("Error in patching the details for %s", userName));
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(String.format("Error in updating user %s", userName));
        }
        catch (IllegalArgumentException e){
            logger.info(String.format("Unable to update details for %s", userName));
        }
        return ResponseEntity.ok(String.format("Successfully updated the user having userName -> %s", userName));
    }
}
