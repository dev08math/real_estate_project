package com.backendservice.dto.PropertyDTOs;

import com.backendservice.dto.PropertyDTOs.fields.AmenitiesDetailsDTO;
import com.backendservice.dto.PropertyDTOs.fields.LocalityDetailsDTO;
import com.backendservice.dto.PropertyDTOs.fields.MainDetailsDTO;
import com.backendservice.dto.PropertyDTOs.fields.RentalDetailsDTO;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@NoArgsConstructor
public class PropertyRegistrationRequest {
    private MainDetailsDTO mainDetails;
    private AmenitiesDetailsDTO amenitiesDetails;
    private RentalDetailsDTO rentalDetails;
    private LocalityDetailsDTO localityDetails;
    private String userID;
    private String userName;
    private List<MultipartFile> images;
}
