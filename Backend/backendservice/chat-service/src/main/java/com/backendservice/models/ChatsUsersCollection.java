package com.backendservice.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;
@Data
@NoArgsConstructor
@Document(collection = "ChatUsersCollection")
public class ChatsUsersCollection {
    @Id
    private String id;
    private String userName;
    private String displayLink;
    private HashMap<String, String> contacts = new HashMap<>();
}
