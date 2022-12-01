package com.backend.realestatebackend.fields.PropertyFields;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AmenitiesDetails {
    private Integer bathrooms;
    private Integer balcony;
    private boolean security;
    private boolean Gym;
    private List<Integer> amenities;
}
