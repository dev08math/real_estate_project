package com.backendservice.utils.fields;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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
