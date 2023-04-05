package com.backendservice.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
@Service
public class CloudinaryService {
    private final Cloudinary cloudinaryClient;

    private final Logger logger = LoggerFactory.getLogger(CloudinaryService.class);

    public CloudinaryService(){
        cloudinaryClient = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "------",
                "api_key", "-----------",
                "api_secret", "-----------"));
    }
    public String uploadToCloudinary(MultipartFile file, String userName, String folderName, String fileName){
        String result = "error";
        try {
            result = (cloudinaryClient.uploader().upload(file.getBytes(), ObjectUtils.asMap(
                    "public_id", "RealEstateApp/Users/" + userName + "/" + folderName + "/" + fileName,
                    "overwrite", true,
                    "resource_type", "auto"
            )).get("secure_url")).toString();
            logger.info(result);
        } catch (Exception e) {
            logger.error("Failed to upload", e);
        }

        return result;
    }
}
