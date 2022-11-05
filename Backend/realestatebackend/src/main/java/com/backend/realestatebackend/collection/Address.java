package com.backend.realestatebackend.collection;

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
