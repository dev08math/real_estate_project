package com.backend.realestatebackend.fields.PropertyFields;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LocalityDetails {
    private String city;
    private String landmark;
    private String locality;
    private List<Float> location;
}
