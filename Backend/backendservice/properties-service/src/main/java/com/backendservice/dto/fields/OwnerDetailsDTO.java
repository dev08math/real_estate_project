package com.backendservice.dto.fields;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OwnerDetailsDTO {
    private String ownerName;
    private String ownerEmail;
    private String ownerPhoneNumber;
}
