package com.backendservice.services;

import com.backendservice.dto.OwnerDetailsResponse;
import com.backendservice.dto.PropertyRegistrationRequest;
import com.backendservice.models.PropertiesCollection;
import com.backendservice.utils.fields.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PropertiesServiceImpl implements PropertiesService{
    @Autowired
    private CloudinaryService cloudinaryService;
    @Autowired
    private PropertiesAssetService propertiesAssetService;
    @Autowired
    private MongoTemplate mt;

    @Override
    public String addNewProperty(PropertyRegistrationRequest propertyRegistrationRequest) {
        WebClient webClient = WebClient.create("http://user-service");
        OwnerDetailsResponse ownerDetailsResponse = webClient.get()
                .uri(builder -> builder.path("/api/user/userDetailsByID").
                        queryParam("userID", propertyRegistrationRequest.getUserID()).build())
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .retrieve()
                .onStatus(HttpStatus.NOT_FOUND::equals, clientResponse -> Mono.empty())
                .bodyToMono(OwnerDetailsResponse.class)
                .block();

        if(ownerDetailsResponse == null)
            throw new IllegalArgumentException("User Not Found with the ID " +
                    propertyRegistrationRequest.getUserID());

        PropertiesCollection propertiesCollection = new PropertiesCollection();

        MainDetails mainDetails = new MainDetails();
        mainDetails.setArea(propertyRegistrationRequest.getMainDetails().getArea());
        mainDetails.setDescription(propertyRegistrationRequest.getMainDetails().getDescription());
        mainDetails.setFloor(propertyRegistrationRequest.getMainDetails().getFloor());
        mainDetails.setPropertyAge(propertyRegistrationRequest.getMainDetails().getPropertyAge());
        mainDetails.setRoomType(propertyRegistrationRequest.getMainDetails().getRoomType());
        mainDetails.setTotalFloors(propertyRegistrationRequest.getMainDetails().getTotalFloors());
        mainDetails.setType(propertyRegistrationRequest.getMainDetails().getType());
        propertiesCollection.setMainDetails(mainDetails);

        LocalityDetails localityDetails = new LocalityDetails();
        localityDetails.setCity(propertyRegistrationRequest.getLocalityDetails().getCity());
        localityDetails.setLandmark(propertyRegistrationRequest.getLocalityDetails().getLandmark());
        localityDetails.setLocality(propertyRegistrationRequest.getLocalityDetails().getLocality());
        localityDetails.setLocation(propertyRegistrationRequest.getLocalityDetails().getLocation());
        localityDetails.setPincode(propertyRegistrationRequest.getLocalityDetails().getPincode());
        propertiesCollection.setLocalityDetails(localityDetails);

        RentalDetails rentalDetails = new RentalDetails();
        rentalDetails.setAvailability(propertyRegistrationRequest.getRentalDetails().getAvailability());
        rentalDetails.setAvailableFrom(propertyRegistrationRequest.getRentalDetails().getAvailableFrom());
        rentalDetails.setDeposit(propertyRegistrationRequest.getRentalDetails().getDeposit());
        rentalDetails.setFurnishing(propertyRegistrationRequest.getRentalDetails().getFurnishing());
        rentalDetails.setNegotiable(propertyRegistrationRequest.getRentalDetails().getNegotiable());
        rentalDetails.setParking(propertyRegistrationRequest.getRentalDetails().getParking());
        rentalDetails.setRent(propertyRegistrationRequest.getRentalDetails().getRent());
        propertiesCollection.setRentalDetails(rentalDetails);

        AmenitiesDetails amenitiesDetails = new AmenitiesDetails();
        amenitiesDetails.setAmenities(propertyRegistrationRequest.getAmenitiesDetails().getAmenities());
        amenitiesDetails.setBalcony(propertyRegistrationRequest.getAmenitiesDetails().getBalcony());
        amenitiesDetails.setBathrooms(propertyRegistrationRequest.getAmenitiesDetails().getBathrooms());
        amenitiesDetails.setGym(propertyRegistrationRequest.getAmenitiesDetails().getGym());
        amenitiesDetails.setSecurity(propertyRegistrationRequest.getAmenitiesDetails().getGym());

        QuickAccess quickAccess = new QuickAccess();
        quickAccess.setCity(propertiesCollection.getLocalityDetails().getCity());
        quickAccess.setCountry("INDIA"); // exclusively for INDIA ☻
        quickAccess.setLoc(propertiesCollection.getLocalityDetails().getLocation());
        quickAccess.setPincode(propertiesCollection.getLocalityDetails().getPincode());
        quickAccess.setRoomType(propertiesCollection.getMainDetails().getRoomType());
        propertiesCollection.setQuickAccess(quickAccess);

        List<MultipartFile> images = propertyRegistrationRequest.getImages();
        List<String> propertyImages = new ArrayList<String>();
        if(images.size()>0){
            for(int i=0; i<images.size(); i++){
                propertyImages.add(cloudinaryService
                        .uploadToCloudinary(images.get(i), propertyRegistrationRequest.getUserName(),
                                  "properties", images.get(i).getOriginalFilename() + i));
            }
            propertiesCollection.setHasPhotos(true);
        }
        else {
            propertyImages.add(cloudinaryService.uploadToCloudinary(
                    propertiesAssetService.getPropFile(), ownerDetailsResponse.getOwnerName(),
                                            "properties", "default_prop" + 0));
            propertiesCollection.setHasPhotos(false);
        }
        propertiesCollection.setImageLinks(propertyImages);
        propertiesCollection.setOwnerId(propertyRegistrationRequest.getUserID());
        propertiesCollection.setTitle("Still have to think");

        Date currentDate = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy");
        String postingDate = formatter.format(currentDate);
        propertiesCollection.setCreatedDate(postingDate);

        mt.save(propertiesCollection);
        return "Success, added the property.";
    }
}
