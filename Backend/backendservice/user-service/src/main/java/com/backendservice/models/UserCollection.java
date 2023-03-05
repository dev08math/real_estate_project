package com.backendservice.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    private  Boolean enabled = false;
    @Builder.Default
    private  Boolean visibility = false;

    @DBRef
    @Builder.Default
    private Set<Roles> roles = new HashSet<>();

    private List<String> myProperties;
    private List<String> bookmarks;
    private String userName;
    private Long phoneNumber;
    private String email;
    private String password;
    private String displayLink;
    private String joiningDate;
}
