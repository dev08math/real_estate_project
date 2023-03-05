package com.backendservice.utils.fields;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class AmenitiesDetails {
    private Integer bathrooms;
    private Integer balcony;
    private Boolean security;
    private Boolean gym;
    private List<Integer> amenities;
}