package com.backend.realestatebackend.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.backend.realestatebackend.collections.PropertiesCollection;

@Repository
public interface PropertiesRepository extends MongoRepository<PropertiesCollection, String>{
    
}
