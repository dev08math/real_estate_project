package com.backend.realestatebackend.models.submodels.fieldmodels;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MainDetailsModel {
    private String type;
    private String roomType;
    private Integer floor;
    private Integer totalFloors;
    private String propertyAge;
    private Float area;
    private String description;
}
