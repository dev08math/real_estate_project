package com.backendservice.controller;

import com.backendservice.dto.PropertyDetails;
import com.backendservice.models.MatchingParameters;
import com.backendservice.models.PropertiesCollection;
import com.backendservice.services.PropertiesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/properties")
public class PropertiesController {
    private final Logger logger = LoggerFactory.getLogger(PropertiesController.class);
    @Autowired
    private PropertiesService propertiesService;

    @PostMapping(value = { "/addNewProperty" }, consumes = { MediaType.MULTIPART_FORM_DATA_VALUE,
                                                             MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<?> addProperty(@RequestPart("propertyData") PropertyDetails propertyDetails,
                                         @RequestPart("imageData") MultipartFile[] files){
        if (files.length > 0) {
            List<MultipartFile> images = new ArrayList<MultipartFile>(Arrays.asList(files));
            propertyDetails.setImages(images);
        }
        String result;
        try {
            result = propertiesService.addNewProperty(propertyDetails);
        }
        catch(Exception e){
            logger.error(e.toString());
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(e.toString());
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/fetchPropertyDetails")
    public ResponseEntity<?> getPropertyDetails(@RequestBody MatchingParameters matchingParameters){
        List<PropertiesCollection> propertiesCollections = propertiesService.getPropertyDetails(matchingParameters);
        if(propertiesCollections.isEmpty()){
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("No properties found");
        }
        return ResponseEntity.ok(propertiesCollections);
    }
}
