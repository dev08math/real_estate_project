package com.backendservice.utils.fields;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class LocalityDetails {
    private String city;
    private String landmark;
    private String locality;
    private Integer pincode;
    private List<Float> location;
}
