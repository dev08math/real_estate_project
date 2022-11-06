package com.backend.realestatebackend.collections;

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
