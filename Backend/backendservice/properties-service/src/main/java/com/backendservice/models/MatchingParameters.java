package com.backendservice.models;

import lombok.Getter;

@Getter
public class MatchingParameters {
    private String ownerId;
    private String propId;
    private String city;
    private String landmark;
    private String locality;
    private Integer pincode;
}
