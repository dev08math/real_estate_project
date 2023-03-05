package com.backendservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JWTResponse {
    private String jwtToken;
    private String id;
    private String userName;
    private String email;
    private String displayLink;
    private List<String> roles;

    @Builder.Default
    private String type = "Bearer";
}
