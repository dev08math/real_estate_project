package com.backend.realestatebackend.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.backend.realestatebackend.collections.UserCollection;

public interface UserRepository extends MongoRepository<UserCollection, String>{
    
}
