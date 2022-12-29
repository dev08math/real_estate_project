package com.backend.realestatebackend.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
// import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
// import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backend.realestatebackend.collections.TokenCollection;
import com.backend.realestatebackend.collections.UserCollection;
import com.backend.realestatebackend.events.RegistrationEvent;
import com.backend.realestatebackend.models.UserLoginModel;
import com.backend.realestatebackend.models.UserRegisterModel;
import com.backend.realestatebackend.payload.responses.jwtResponse;
import com.backend.realestatebackend.security.Jwt.JwtUtils;
import com.backend.realestatebackend.security.details.CustomUserDetailsImpl;
import com.backend.realestatebackend.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtutils;

    @Autowired
    private ApplicationEventPublisher publisher;

    private static String generateUrl(HttpServletRequest request) {
        return "http://" +
                request.getServerName() +
                ":" +
                request.getServerPort() +
                request.getContextPath();
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody UserLoginModel userLoginModel) {

        CustomUserDetailsImpl userDetails = new CustomUserDetailsImpl();
        String jwt = "";
        List<String> roles = new ArrayList<String>();
        try{
            String username = userService.getUsernameByEmail(userLoginModel.getEmail());
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, userLoginModel.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);
            jwt = jwtutils.generateJwtToken(authentication);

            userDetails = (CustomUserDetailsImpl) authentication.getPrincipal();
            roles = userDetails.getAuthorities().stream()
                    .map(item -> item.getAuthority())
                    .collect(Collectors.toList());
        }
        catch(Exception e){
        return ResponseEntity
            .status(HttpStatus.FORBIDDEN)
            .body("Invalid Email / Password");
        }

        return ResponseEntity.ok(new jwtResponse(jwt, userDetails.getId(), userDetails.getUsername(),
                userDetails.getEmail(), userDetails.getDp(), roles, "Bearer"));
    }

    @PostMapping("/register")
    public UserCollection generateNewUser(@RequestBody UserRegisterModel userModel, final HttpServletRequest request) {
        UserCollection user = userService.registerNewUser(userModel);
        publisher.publishEvent(new RegistrationEvent(user, generateUrl(request)));
        return user;
    }

    @PostMapping("/verifyregistration")
    public String verifyRegistration(@RequestParam("token") String token) {
        String result = userService.validateVerficationToken(token);
        if (result.equalsIgnoreCase("valid"))
            return "Token Validated";
        return result;
    };

    @PostMapping("/resendverificationtoken")
    public String resendVerificationToken(@RequestParam("token") String token, final HttpServletRequest request) {
        TokenCollection tokenCollection = userService.generateNewToken(token);
        if (tokenCollection == null)
            return "Invalid Token";
        return generateUrl(request) + "user/verifyregistration?token="
                + tokenCollection.getVerificationToken().getToken();
    }
}
