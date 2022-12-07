package com.backend.realestatebackend.models;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.backend.realestatebackend.models.submodels.fieldmodels.AmenitiesDetailsModel;
import com.backend.realestatebackend.models.submodels.fieldmodels.LocalityDetailsModel;
import com.backend.realestatebackend.models.submodels.fieldmodels.MainDetailsModel;
import com.backend.realestatebackend.models.submodels.fieldmodels.RentalDetailsModel;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PropertyModel {
    private MainDetailsModel mainDetails;
    private AmenitiesDetailsModel amenitiesDetails;
    private RentalDetailsModel rentalDetails;
    private LocalityDetailsModel localityDetails;
    private List<MultipartFile> images;
}
