package com.backendservice.controller;

import com.backendservice.dto.JWTResponse;
import com.backendservice.dto.UserDTOs.UserDetailsRequest;
import com.backendservice.dto.UserDTOs.UserRegRequest;
import com.backendservice.jwt.JWTUtil;
import com.backendservice.security.CustomUserDetailsImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/public")
public class OpenController {
    Logger logger = LoggerFactory.getLogger(OpenController.class);
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JWTUtil jwtUtil;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    UserDetailsService userDetailsService;


    @PostMapping("/user/userRegistration")
    public ResponseEntity<?> registerUser(@RequestBody UserRegRequest userRegRequest){

        Map<String, String> bodyMap = new HashMap<>();
        bodyMap.put("userName", userRegRequest.getUserName());
        bodyMap.put("password", passwordEncoder.encode(userRegRequest.getPassword()));
        bodyMap.put("email", userRegRequest.getEmail());
        bodyMap.put("phoneNumber", userRegRequest.getPhoneNumber());

        WebClient webClient = WebClient.create("http://user-service");
        JWTResponse jwtResponse;
        try{
            jwtResponse = webClient.post()
                    .uri("/api/user/registerUser")
                    .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                    .body(BodyInserters.fromValue(bodyMap))
                    .retrieve()
                    .onStatus(HttpStatus.NOT_FOUND::equals, clientResponse -> Mono.empty())
                    .bodyToMono(JWTResponse.class)
                    .block();
            if(jwtResponse == null) throw  new SecurityException();
        } catch (Exception e) {
            logger.error(e.toString());
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Unable to create a new account");
        }
        Map<String, String> userCreds= new HashMap<>();
        userCreds.put("userName", userRegRequest.getUserName());
        userCreds.put("password", userRegRequest.getPassword());
        WebClient webClient2 = WebClient.create("http://auth-service");
        jwtResponse = webClient2.post()
                    .uri("/api/secure/user/login")
                    .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                    .body(BodyInserters.fromValue(userCreds))
                    .retrieve()
                    .onStatus(HttpStatus.NOT_FOUND::equals, clientResponse -> Mono.empty())
                    .bodyToMono(JWTResponse.class)
                    .block();
        return ResponseEntity.ok(jwtResponse);
    }

    @PostMapping("/auth/user/login")
    public ResponseEntity<?> authenticateUser(@RequestBody UserDetailsRequest userDetailsRequest) {

        String jwt;
        List<String> roles;
        CustomUserDetailsImpl userDetails;

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userDetailsRequest.getUserName(),
                            userDetailsRequest.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);
            jwt = jwtUtil.generateJwtToken(authentication);

            userDetails = (CustomUserDetailsImpl) authentication.getPrincipal();
            roles = userDetails.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            logger.error(String.format("The invoked error is %s", e));
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body("Invalid Email / Password");
        }

        JWTResponse jwtResponse = JWTResponse.builder().jwtToken(jwt).email(userDetails.getEmail())
                .displayLink(userDetails.getDisplayLink()).roles(roles).id(userDetails.getId())
                .userName(userDetails.getUsername()).build();
        return ResponseEntity.ok(jwtResponse);
    }

    private String parseJwt(HttpServletRequest request) {
        String headerAuth = request.getHeader("Authorization");

        if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
            return headerAuth.substring(7);
        }
        return null;
    }

    @GetMapping("/auth/chat/login")
    public ResponseEntity<?> authenticateChat(HttpServletRequest request) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken;
        try {
            String jwt = parseJwt(request);
            if (jwt != null && jwtUtil.validateJwtToken(jwt)) {
                String username = jwtUtil.getUserNameFromJwtToken(jwt);

                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null,
                        userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
            else{
                logger.error("Unable to find Auth token.");
                return ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body("Unable to login to chat server");
            }
        } catch (Exception e) {
            logger.error(String.format("Cannot set user authentication: %s", e));
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Unable to login to chat server");
        }
        return ResponseEntity.ok(usernamePasswordAuthenticationToken.toString());
    }

}
