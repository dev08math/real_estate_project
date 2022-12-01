package com.backend.realestatebackend.collections;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
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
    private String userId;

    @Builder.Default
    private  boolean enabled = false;

    @Builder.Default
    private  boolean visibility = false;

    // @Transient
    // private MultipartFile dp;

    private List<String> myproperties;
    private List<String> bookmarks;
    
    private String name;
    private Long phoneNumber;
    private String email;
    private String password;
    private String dp;
    private String joiningDate;
}
