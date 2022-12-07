package com.backend.realestatebackend.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.backend.realestatebackend.collections.PropertiesCollection;
import com.backend.realestatebackend.fields.PropertyFields.AmenitiesDetails;
import com.backend.realestatebackend.fields.PropertyFields.LocalityDetails;
import com.backend.realestatebackend.fields.PropertyFields.MainDetails;
import com.backend.realestatebackend.fields.PropertyFields.QuickAccess;
import com.backend.realestatebackend.fields.PropertyFields.RentalDetails;
import com.backend.realestatebackend.models.PropertyModel;

@Service
public class PropertiesServiceImpl implements PropertiesService {

    @Autowired
    private AssetProvider assetProvider;

    @Autowired
    private CloudinaryService cloudinaryService;

    @Autowired
    private MongoTemplate mt;

    @Override
    public PropertiesCollection addNewProperty(PropertyModel propertyModel) {
        PropertiesCollection propertiesCollection = new PropertiesCollection();

        MainDetails mainDetails = new MainDetails();
        mainDetails.setArea(propertyModel.getMainDetails().getArea());
        mainDetails.setDescription(propertyModel.getMainDetails().getDescription());
        mainDetails.setFloor(propertyModel.getMainDetails().getFloor());
        mainDetails.setPropertyAge(propertyModel.getMainDetails().getPropertyAge());
        mainDetails.setRoomType(propertyModel.getMainDetails().getRoomType());
        mainDetails.setTotalFloors(propertyModel.getMainDetails().getTotalFloors());
        mainDetails.setType(propertyModel.getMainDetails().getType());
        propertiesCollection.setMainDetails(mainDetails);

        LocalityDetails localityDetails = new LocalityDetails();
        localityDetails.setCity(propertyModel.getLocalityDetails().getCity());
        localityDetails.setLandmark(propertyModel.getLocalityDetails().getLandmark());
        localityDetails.setLocality(propertyModel.getLocalityDetails().getLocality());
        localityDetails.setLocation(propertyModel.getLocalityDetails().getLocation());
        localityDetails.setPincode(propertyModel.getLocalityDetails().getPincode());
        propertiesCollection.setLocalityDetails(localityDetails);

        RentalDetails rentalDetails = new RentalDetails();
        rentalDetails.setAvailability(propertyModel.getRentalDetails().getAvailability());
        rentalDetails.setAvailableFrom(propertyModel.getRentalDetails().getAvailableFrom());
        rentalDetails.setDeposit(propertyModel.getRentalDetails().getDeposit());
        rentalDetails.setFurnishing(propertyModel.getRentalDetails().getFurnishing());
        rentalDetails.setNegotiable(propertyModel.getRentalDetails().getNegotiable());
        rentalDetails.setParking(propertyModel.getRentalDetails().getParking());
        rentalDetails.setRent(propertyModel.getRentalDetails().getRent());
        propertiesCollection.setRentalDetails(rentalDetails);

        AmenitiesDetails amenitiesDetails = new AmenitiesDetails();
        amenitiesDetails.setAmenities(propertyModel.getAmenitiesDetails().getAmenities());
        amenitiesDetails.setBalcony(propertyModel.getAmenitiesDetails().getBalcony());
        amenitiesDetails.setBathrooms(propertyModel.getAmenitiesDetails().getBathrooms());
        amenitiesDetails.setGym(propertyModel.getAmenitiesDetails().getGym());
        amenitiesDetails.setSecurity(propertyModel.getAmenitiesDetails().getGym());

        QuickAccess quickAccess = new QuickAccess();
        quickAccess.setCity(propertiesCollection.getLocalityDetails().getCity());
        quickAccess.setCountry("INDIA"); // exclusively for INDIA ☻
        quickAccess.setLoc(propertiesCollection.getLocalityDetails().getLocation());
        quickAccess.setPincode(propertiesCollection.getLocalityDetails().getPincode());
        quickAccess.setRoomType(propertiesCollection.getMainDetails().getRoomType());
        propertiesCollection.setQuickAccess(quickAccess);

        List<MultipartFile> images = propertyModel.getImages();
        List<String> propertyImages = new ArrayList<String>();
        if(images.size()>0){
            for(int i=0; i<images.size(); i++){
                propertyImages.add(cloudinaryService.uploadToCloudinary(images.get(i), "dummyUser", "properties", "p" + i));
            }
            propertiesCollection.setHasPhotos(true);
        }
        else {
            propertyImages.add(cloudinaryService.uploadToCloudinary(assetProvider.getPropFile(), "dummyUser", "properties", "p" + 0));
            propertiesCollection.setHasPhotos(false);
        }
        propertiesCollection.setImageLinks(propertyImages);
        propertiesCollection.setOwnerId("dummyUser's ID");
        propertiesCollection.setTitle("Still have to think");

        Date currentDate = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy");
        String postingDate = formatter.format(currentDate);
        propertiesCollection.setCreatedDate(postingDate);

        mt.save(propertiesCollection);
        return propertiesCollection;
    }
    
}
