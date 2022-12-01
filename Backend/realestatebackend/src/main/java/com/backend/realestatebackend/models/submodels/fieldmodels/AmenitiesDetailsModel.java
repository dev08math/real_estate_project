package com.backend.realestatebackend.models.submodels.fieldmodels;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AmenitiesDetailsModel {
    private Integer bathrooms;
    private Integer balcony;
    private boolean security;
    private boolean Gym;
    private List<Integer> amenities;
}
