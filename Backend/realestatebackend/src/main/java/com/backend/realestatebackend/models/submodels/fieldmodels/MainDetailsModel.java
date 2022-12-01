package com.backend.realestatebackend.models.submodels.fieldmodels;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MainDetailsModel {
    private String type;
    private String roomType;
    private String floor;
    private String totalFloors;
    private String propertyAge;
    private String area;
    private String description;
}
