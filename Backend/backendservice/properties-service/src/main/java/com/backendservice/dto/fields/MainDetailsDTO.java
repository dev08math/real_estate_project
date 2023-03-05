package com.backendservice.dto.fields;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MainDetailsDTO {
    private String type;
    private String roomType;
    private Integer floor;
    private Integer totalFloors;
    private String propertyAge;
    private Float area;
    private String description;
}
