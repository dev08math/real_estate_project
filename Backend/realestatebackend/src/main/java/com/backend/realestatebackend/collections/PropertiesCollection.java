package com.backend.realestatebackend.collections;

// import java.util.Date;
import java.util.List;

// import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
// import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import com.backend.realestatebackend.fields.PropertyFields.Address;
import com.backend.realestatebackend.fields.PropertyFields.AmenitiesDetails;
import com.backend.realestatebackend.fields.PropertyFields.LocalityDetails;
import com.backend.realestatebackend.fields.PropertyFields.MainDetails;
import com.backend.realestatebackend.fields.PropertyFields.RentalDetails;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "Properties")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PropertiesCollection {

    @Id
    private String propId;

    // @Transient    
    // // this is list of Multipart will be serializable but not persistable in the db
    // // this member is only used transfer data via serilization to upload files via cloudinary service 
    // private List<MultipartFile> photos;
    
    @Builder.Default
    private  boolean activate = false;

    private String ownerId;
    private String title; 

    private MainDetails mainDetails;

    private LocalityDetails localityDetails;

    private RentalDetails rentalDetails;

    private AmenitiesDetails amenitiesDetails;

    private Address address;

    private List<String> photos;
    private boolean hasPhotos;

    private String createdDate;
    private String lastModifiedDate;

}
