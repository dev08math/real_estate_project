package com.backend.realestatebackend.collections;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.backend.realestatebackend.models.ERole;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Document(collection = "Roles")
public class Roles {
    @Id
    private String id;

    private ERole eRole;
}
