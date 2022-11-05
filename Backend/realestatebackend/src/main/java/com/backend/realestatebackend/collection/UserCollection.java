package com.backend.realestatebackend.collection;

import java.util.Date;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.web.multipart.MultipartFile;

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

    @CreatedDate
    private Date joiningDate;

    @Builder.Default
    private  boolean enabled = false;

    @DocumentReference(lazy = true)
    private PropertiesCollection myproperties;

    @Transient
    private MultipartFile dp;
    
    private String name;
    private Long phoneNumber;
    private String email;
    private String password;
    

}
