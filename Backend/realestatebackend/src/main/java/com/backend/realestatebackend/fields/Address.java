package com.backend.realestatebackend.fields;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Address {

    private String city;
    private String country;
    private String state;
    private Integer pincode;
}
