package com.backendservice.models;

import com.backendservice.utils.fields.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "PropertiesCollection")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PropertiesCollection {

    @Id
    private String propId;

    @Builder.Default
    private  Boolean activate = false;

    private String ownerId;
    private String title;

    private MainDetails mainDetails;
    private LocalityDetails localityDetails;
    private RentalDetails rentalDetails;
    private AmenitiesDetails amenitiesDetails;
    private QuickAccess quickAccess;
    private List<String> imageLinks;
    private Boolean hasPhotos;

    private String createdDate;
    private String lastModifiedDate;

}
