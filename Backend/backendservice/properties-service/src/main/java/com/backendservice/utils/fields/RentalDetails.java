package com.backendservice.utils.fields;

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
    private String preference;
    private Boolean negotiable;
    private Boolean parking;
}
