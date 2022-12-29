package com.backend.realestatebackend.payload.responses;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class jwtResponse {
    private String token;
	private String id;
	private String username;
	private String email;
	private String dp;
	private List <String> roles;

    @Builder.Default
    private String type = "Bearer";
}
