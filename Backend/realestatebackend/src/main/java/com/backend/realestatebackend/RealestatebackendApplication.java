package com.backend.realestatebackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.backend.realestatebackend.dao.PropertiesRepository;
import com.backend.realestatebackend.dao.UserRepository;

@SpringBootApplication
@EnableMongoRepositories(basePackageClasses = {PropertiesRepository.class, UserRepository.class})
@EnableMongoAuditing
public class RealestatebackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(RealestatebackendApplication.class, args);
	}

}