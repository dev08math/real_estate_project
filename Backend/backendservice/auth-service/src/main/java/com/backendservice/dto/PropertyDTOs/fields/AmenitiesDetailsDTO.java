package com.backendservice.dto.PropertyDTOs.fields;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class AmenitiesDetailsDTO {
    private Integer bathrooms;
    private Integer balcony;
    private Boolean security;
    private Boolean gym;
    private List<Integer> amenities;
}
