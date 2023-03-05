package com.backendservice.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OwnerDetailsResponse {
    private String ownerName;
    private String ownerEmail;
    private String ownerPhoneNumber;
}
