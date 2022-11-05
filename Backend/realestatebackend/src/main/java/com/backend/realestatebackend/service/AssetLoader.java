package com.backend.realestatebackend.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class AssetLoader {

    private static String assetsPath = AssetLoader.class.getResource("....").toString() + "assets";

    private File default_dp;
    private InputStream stream;
    private MultipartFile dp_file; 

    public AssetLoader() throws IOException{
        default_dp = new File(assetsPath + "default_dp.png");
        try {
            stream =  new FileInputStream(default_dp);
        } catch (Exception  ex) {
            throw new IllegalArgumentException("Invalid file: " + ex, ex);
        } 
        dp_file = new MockMultipartFile("default_dp.png", default_dp.getName(), MediaType.TEXT_HTML_VALUE, stream);
        // new MockMultipartFile("file", zipFile.getName(),String.valueOf(MediaType.MULTIPART_FORM_DATA), stream);
        // new MockMultipartFile(multipartFileParameterName,file.getName(),Files.probeContentType(file.toPath()),IOUtils.toByteArray(input));}
    }

    public MultipartFile getDpFile(){
        return dp_file;
    }
}
