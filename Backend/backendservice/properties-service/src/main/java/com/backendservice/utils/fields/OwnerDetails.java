package com.backendservice.utils.fields;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OwnerDetails {
    private String ownerName;
    private String ownerEmail;
    private String ownerPhoneNumber;
}
