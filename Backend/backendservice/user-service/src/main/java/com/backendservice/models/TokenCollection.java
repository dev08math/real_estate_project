package com.backendservice.models;

import com.backendservice.utils.PasswordToken;
import com.backendservice.utils.VerificationToken;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

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
