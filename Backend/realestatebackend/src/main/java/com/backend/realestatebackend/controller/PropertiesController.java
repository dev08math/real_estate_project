package com.backend.realestatebackend.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.backend.realestatebackend.collections.PropertiesCollection;
import com.backend.realestatebackend.models.PropertyModel;
import com.backend.realestatebackend.service.PropertiesService;

@RestController
@RequestMapping("/properties")
public class PropertiesController {

    @Autowired
    private PropertiesService propertiesService;

    @PostMapping(value = { "/newproperty" }, consumes = { MediaType.MULTIPART_FORM_DATA_VALUE,
                                                          MediaType.APPLICATION_JSON_VALUE })
    public PropertiesCollection addNewProperty(@RequestPart("propertyData") PropertyModel propertyModel,
                                               @RequestPart("imageData") MultipartFile[] files) {
        if (files.length > 0) {
            List<MultipartFile> images = new ArrayList<MultipartFile>();
            for (MultipartFile file : files) {
                images.add(file);
            }
            propertyModel.setImages(images);
        }
        PropertiesCollection propertiesCollection = propertiesService.addNewProperty(propertyModel);
        return propertiesCollection;
    }
}
