package com.backendservice.dto;

import com.backendservice.dto.fields.AmenitiesDetailsDTO;
import com.backendservice.dto.fields.LocalityDetailsDTO;
import com.backendservice.dto.fields.MainDetailsDTO;
import com.backendservice.dto.fields.RentalDetailsDTO;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
@Data
@NoArgsConstructor
public class PropertyDetails {
    private MainDetailsDTO mainDetails;
    private AmenitiesDetailsDTO amenitiesDetails;
    private RentalDetailsDTO rentalDetails;
    private LocalityDetailsDTO localityDetails;
    private String userID;
    private String userName;
    private List<MultipartFile> images;
}
