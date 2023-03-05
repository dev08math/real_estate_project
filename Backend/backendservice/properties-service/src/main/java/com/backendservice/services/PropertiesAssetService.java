package com.backendservice.services;

import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
@Service
public class PropertiesAssetService {
    ClassPathResource default_prop;
    private final MultipartFile prop_file;

    public PropertiesAssetService() throws IOException {
        default_prop = new ClassPathResource("assets/default_prop.png");

        InputStream prop_stream;
        try {
            prop_stream = default_prop.getInputStream();
        } catch (Exception  ex) {
            throw new IllegalArgumentException("Invalid file: " + ex, ex);
        }
        prop_file = new MockMultipartFile("default_prop.png", default_prop.getFilename(), MediaType.TEXT_HTML_VALUE, prop_stream);
        // new MockMultipartFile("file", zipFile.getName(),String.valueOf(MediaType.MULTIPART_FORM_DATA), stream);
        // new MockMultipartFile(multipartFileParameterName,file.getName(),Files.probeContentType(file.toPath()),IOUtils.toByteArray(input));}
    }

    public MultipartFile getPropFile(){
        return prop_file;
    }
}
