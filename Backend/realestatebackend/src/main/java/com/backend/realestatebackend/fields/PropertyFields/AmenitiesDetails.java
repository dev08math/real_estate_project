package com.backend.realestatebackend.fields.PropertyFields;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AmenitiesDetails {
    private Integer bathrooms;
    private Integer balcony;
    private Boolean security;
    private Boolean gym;
    private List<Integer> amenities;
}
