package com.backend.realestatebackend.fields.PropertyFields;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class QuickAccess {
    private List<Float> loc;
    private String city;
    private String country;
    private String state;
    private Integer pincode;
    private String roomType;
}
