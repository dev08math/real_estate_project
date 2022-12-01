package com.backend.realestatebackend.fields.PropertyFields;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RentalDetails {
    private String availability;
    private Integer rent;
    private Integer deposit;
    private String availableFrom;
    private String furnishing;
    private String preferrence;
    private boolean negotiable;
    private boolean parking;
}
