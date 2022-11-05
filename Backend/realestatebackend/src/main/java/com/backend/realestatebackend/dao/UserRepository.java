package com.backend.realestatebackend.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.backend.realestatebackend.collection.UserCollection;

@Repository
public interface UserRepository extends MongoRepository<UserCollection, String>{
    
}
