package com.backendservice.services;

import com.backendservice.dto.OwnerDetailsResponse;
import com.backendservice.dto.PropertyDetails;
import com.backendservice.models.MatchingParameters;
import com.backendservice.models.PropertiesCollection;
import com.backendservice.utils.fields.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
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
    private NodeService nodeService;
    @Autowired
    private MongoTemplate mt;

    @Override
    public String addNewProperty(PropertyDetails propertyDetails) {
        WebClient webClient = WebClient.create("http://user-service");
        OwnerDetailsResponse ownerDetailsResponse = webClient.get()
                .uri(builder -> builder.path("/api/user/userDetailsByID").
                        queryParam("userID", propertyDetails.getUserID()).build())
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .retrieve()
                .onStatus(HttpStatus.NOT_FOUND::equals, clientResponse -> Mono.empty())
                .bodyToMono(OwnerDetailsResponse.class)
                .block();

        if(ownerDetailsResponse == null)
            throw new IllegalArgumentException("User Not Found with the ID " +
                    propertyDetails.getUserID());

        PropertiesCollection propertiesCollection = new PropertiesCollection();

        MainDetails mainDetails = new MainDetails();
        mainDetails.setArea(propertyDetails.getMainDetails().getArea());
        mainDetails.setDescription(propertyDetails.getMainDetails().getDescription());
        mainDetails.setFloor(propertyDetails.getMainDetails().getFloor());
        mainDetails.setPropertyAge(propertyDetails.getMainDetails().getPropertyAge());
        mainDetails.setRoomType(propertyDetails.getMainDetails().getRoomType());
        mainDetails.setTotalFloors(propertyDetails.getMainDetails().getTotalFloors());
        mainDetails.setType(propertyDetails.getMainDetails().getType());
        propertiesCollection.setMainDetails(mainDetails);

        LocalityDetails localityDetails = new LocalityDetails();
        localityDetails.setCity(propertyDetails.getLocalityDetails().getCity());
        localityDetails.setLandmark(propertyDetails.getLocalityDetails().getLandmark());
        localityDetails.setLocality(propertyDetails.getLocalityDetails().getLocality());
        localityDetails.setLocation(propertyDetails.getLocalityDetails().getLocation());
        localityDetails.setPincode(propertyDetails.getLocalityDetails().getPincode());
        propertiesCollection.setLocalityDetails(localityDetails);

        RentalDetails rentalDetails = new RentalDetails();
        rentalDetails.setAvailability(propertyDetails.getRentalDetails().getAvailability());
        rentalDetails.setAvailableFrom(propertyDetails.getRentalDetails().getAvailableFrom());
        rentalDetails.setDeposit(propertyDetails.getRentalDetails().getDeposit());
        rentalDetails.setFurnishing(propertyDetails.getRentalDetails().getFurnishing());
        rentalDetails.setNegotiable(propertyDetails.getRentalDetails().getNegotiable());
        rentalDetails.setParking(propertyDetails.getRentalDetails().getParking());
        rentalDetails.setRent(propertyDetails.getRentalDetails().getRent());
        propertiesCollection.setRentalDetails(rentalDetails);

        AmenitiesDetails amenitiesDetails = new AmenitiesDetails();
        amenitiesDetails.setAmenities(propertyDetails.getAmenitiesDetails().getAmenities());
        amenitiesDetails.setBalcony(propertyDetails.getAmenitiesDetails().getBalcony());
        amenitiesDetails.setBathrooms(propertyDetails.getAmenitiesDetails().getBathrooms());
        amenitiesDetails.setGym(propertyDetails.getAmenitiesDetails().getGym());
        amenitiesDetails.setSecurity(propertyDetails.getAmenitiesDetails().getGym());

        QuickAccess quickAccess = new QuickAccess();
        quickAccess.setCity(propertiesCollection.getLocalityDetails().getCity());
        quickAccess.setCountry("INDIA"); // exclusively for INDIA ☻
        quickAccess.setLoc(propertiesCollection.getLocalityDetails().getLocation());
        quickAccess.setPincode(propertiesCollection.getLocalityDetails().getPincode());
        quickAccess.setRoomType(propertiesCollection.getMainDetails().getRoomType());
        propertiesCollection.setQuickAccess(quickAccess);

        List<MultipartFile> images = propertyDetails.getImages();
        List<String> propertyImages = new ArrayList<>();
        if(images.size()>0){
            for(int i=0; i<images.size(); i++){
                propertyImages.add(cloudinaryService
                        .uploadToCloudinary(images.get(i), propertyDetails.getUserName(),
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
        propertiesCollection.setOwnerId(propertyDetails.getUserID());
        propertiesCollection.setTitle("Still have to think");

        Date currentDate = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy");
        String postingDate = formatter.format(currentDate);
        propertiesCollection.setCreatedDate(postingDate);

        nodeService.insert(String.format("%s, %s, %s", propertyDetails.getLocalityDetails().getLocation(),
                                                              propertyDetails.getLocalityDetails().getLocality(),
                                                              propertyDetails.getLocalityDetails().getCity()));
        mt.save(propertiesCollection);
        return propertiesCollection.getPropId();
    }

    @Override
    public List<PropertiesCollection> getPropertyDetails(MatchingParameters matchingParameters) {
        List<PropertiesCollection> propertyDetails;
        Query query = new Query().addCriteria(
                Criteria.where("").orOperator(
                    Criteria.where("ownerId").is(
                            matchingParameters.getOwnerId() == null ? "" : matchingParameters.getOwnerId()),
                    Criteria.where("propId").is(
                            matchingParameters.getPropId() == null ? "" : matchingParameters.getPropId()),
                    Criteria.where("localityDetails.city").is(
                            matchingParameters.getCity() == null ? "" : matchingParameters.getCity()),
                    Criteria.where("localityDetails.landmark").is(
                            matchingParameters.getLandmark() == null ? "" : matchingParameters.getLandmark()),
                    Criteria.where("localityDetails.locality").is(
                            matchingParameters.getLocality()== null ? "" : matchingParameters.getLocality()),
                    Criteria.where("localityDetails.pincode").is(
                            matchingParameters.getPincode() == null ? -1 : matchingParameters.getPincode())
                )
        );
        propertyDetails = mt.find(query, PropertiesCollection.class, "PropertiesCollection");
        return propertyDetails;
    }
}
