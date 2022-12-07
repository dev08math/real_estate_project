package com.backend.realestatebackend.service;

import com.backend.realestatebackend.collections.PropertiesCollection;
import com.backend.realestatebackend.models.PropertyModel;

public interface PropertiesService {

    PropertiesCollection addNewProperty(PropertyModel propertyModel);
    
}
