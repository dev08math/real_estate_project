package com.backend.realestatebackend.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class AssetProvider {

    private static String assetsPath = "classpath:assets/";

    private File default_dp, default_prop;
    private InputStream dp_stream, prop_stream;
    private MultipartFile dp_file, prop_file; 

    public AssetProvider() throws IOException{
        default_dp = ResourceUtils.getFile(assetsPath + "default_dp.png");
        default_prop = ResourceUtils.getFile(assetsPath + "default_prop.png");
        try {
            dp_stream = new FileInputStream(default_dp);
            prop_stream = new FileInputStream(default_prop);
        } catch (Exception  ex) {
            throw new IllegalArgumentException("Invalid file: " + ex, ex);
        } 
        dp_file = new MockMultipartFile("default_dp.png", default_dp.getName(), MediaType.TEXT_HTML_VALUE, dp_stream);
        prop_file = new MockMultipartFile("default_prop.png", default_prop.getName(), MediaType.TEXT_HTML_VALUE, prop_stream);
        // new MockMultipartFile("file", zipFile.getName(),String.valueOf(MediaType.MULTIPART_FORM_DATA), stream);
        // new MockMultipartFile(multipartFileParameterName,file.getName(),Files.probeContentType(file.toPath()),IOUtils.toByteArray(input));}
    }

    public MultipartFile getDpFile(){
        return dp_file;
    }
    
    public MultipartFile getPropFile(){
        return prop_file;
    }
}
