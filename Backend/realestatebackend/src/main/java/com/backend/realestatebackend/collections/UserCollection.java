package com.backend.realestatebackend.collections;

// import java.util.Date;

// import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "UserCollection")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserCollection {
    
    @Id
    private String id;

    // @CreatedDate
    // private Date joiningDate;

    @Builder.Default
    private  boolean enabled = false;

    @DocumentReference(lazy = true)
    private PropertiesCollection myproperties;

    // @Transient
    // private MultipartFile dp;
    
    private String name;
    private Long phoneNumber;
    private String email;
    private String password;
    private String dp;
    private String joiningDate;
}
