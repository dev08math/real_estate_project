package com.backendservice.models;

import com.backendservice.utils.ERole;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@Document(collection = "Roles")
public class Roles {
    @Id
    private String id;

    private ERole eRole;
}

