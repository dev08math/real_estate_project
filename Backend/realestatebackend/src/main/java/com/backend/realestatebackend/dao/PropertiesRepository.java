package com.backend.realestatebackend.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.backend.realestatebackend.collections.PropertiesCollection;

public interface PropertiesRepository extends MongoRepository<PropertiesCollection, String>{
    
}
