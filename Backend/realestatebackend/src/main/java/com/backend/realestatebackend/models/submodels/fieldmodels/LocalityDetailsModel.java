package com.backend.realestatebackend.models.submodels.fieldmodels;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LocalityDetailsModel {
    private String city;
    private String landmark;
    private String locality;
    private List<Float> location;
}
