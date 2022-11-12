package com.backend.realestatebackend.collections;

// import java.util.Date;
import java.util.List;

// import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
// import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import com.backend.realestatebackend.fields.Address;
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
    private  boolean visibility = true;

    private String ownerId;
    private String title;

    private String type;
    private String rooms;
    private String price;
    private boolean purchaseable;

    private List<String> loc;
    private Address address;

    private List<String> amenities;
    private List<String> photos;
    private boolean hasPhotos;

    private String createdDate;
    private String lastModifiedDate;

}
