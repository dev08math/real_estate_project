package com.backendservice.services;

import com.backendservice.dto.PropertyDetails;
import com.backendservice.models.MatchingParameters;
import com.backendservice.models.PropertiesCollection;

import java.util.List;

public interface PropertiesService {
    String addNewProperty(PropertyDetails propertyDetails);
    List<PropertiesCollection> getPropertyDetails(MatchingParameters matchingParameters);
}
