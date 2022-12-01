package com.backend.realestatebackend.fields.PropertyFields;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MainDetails {
    private String type;
    private String roomType;
    private String floor;
    private String totalFloors;
    private String propertyAge;
    private String area;
    private String description;
}
