package com.backend.realestatebackend.collections;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.backend.realestatebackend.fields.PasswordToken;
import com.backend.realestatebackend.fields.VerificationToken;

import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "TokenCollection")
@Data
@NoArgsConstructor
public class TokenCollection {
    @Id
    private String tokenId;

    private String userId;

    private PasswordToken passwordtoken;
    private VerificationToken verificationToken;

}
