package com.backend.realestatebackend.models.submodels.fieldmodels;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RentalDetailsModel {
    private String availability;
    private Integer rent;
    private Integer deposit;
    private String availableFrom;
    private String furnishing;
    private String preferrence;
    private boolean negotiable;
    private boolean parking;
}
