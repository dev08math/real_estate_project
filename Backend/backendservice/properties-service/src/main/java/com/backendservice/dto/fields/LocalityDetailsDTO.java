package com.backendservice.dto.fields;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
public class LocalityDetailsDTO {
    private String city;
    private String landmark;
    private String locality;
    private Integer pincode;
    private List<Float> location;
}
