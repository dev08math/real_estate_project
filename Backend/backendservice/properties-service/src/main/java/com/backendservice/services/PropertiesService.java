package com.backendservice.services;

import com.backendservice.dto.PropertyRegistrationRequest;

public interface PropertiesService {
    String addNewProperty(PropertyRegistrationRequest propertyRegistrationRequest);
}
