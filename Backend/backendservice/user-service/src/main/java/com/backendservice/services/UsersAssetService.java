package com.backendservice.services;

import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
@Service
public class UsersAssetService {
    ClassPathResource default_dp;
    private final MultipartFile dp_file;

    public UsersAssetService() throws IOException {
        default_dp = new ClassPathResource("assets/default_dp.png");

        InputStream dp_stream;
        try {
            dp_stream = default_dp.getInputStream();
        } catch (Exception  ex) {
            throw new IllegalArgumentException("Invalid file: " + ex, ex);
        }
        dp_file = new MockMultipartFile("default_dp.png", default_dp.getFilename(), MediaType.TEXT_HTML_VALUE, dp_stream);
        // new MockMultipartFile("file", zipFile.getName(),String.valueOf(MediaType.MULTIPART_FORM_DATA), stream);
        // new MockMultipartFile(multipartFileParameterName,file.getName(),Files.probeContentType(file.toPath()),IOUtils.toByteArray(input));}
    }

    public MultipartFile getDpFile(){
        return dp_file;
    }

}
