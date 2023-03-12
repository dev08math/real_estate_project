package com.backendservice.controller;

import com.backendservice.dto.PropertyDTOs.PropertyRegistrationRequest;
import com.backendservice.dto.UserDTOs.OwnerDetailsResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import utils.MultipartInputStreamFileResource;


@RestController
@RequestMapping("/api/secure/property")
public class PropertySecurityController {
    private final Logger logger = LoggerFactory.getLogger(PropertySecurityController.class);

    @PostMapping(value = { "/propertyRegistration" }, consumes = { MediaType.MULTIPART_FORM_DATA_VALUE,
            MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<?> addProperty(@RequestPart("propertyData") PropertyRegistrationRequest propertyRegistrationRequest,
                                         @RequestPart("imageData") MultipartFile[] files){

        WebClient webClient = WebClient.create("http://user-service");
        OwnerDetailsResponse ownerDetailsResponse = webClient.get()
                .uri(builder -> builder.path("/api/user/userDetailsByID").
                        queryParam("userID", propertyRegistrationRequest.getUserID()).build())
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .retrieve()
                .onStatus(HttpStatus.NOT_FOUND::equals, clientResponse -> Mono.empty())
                .bodyToMono(OwnerDetailsResponse.class)
                .block();

        try{
            if(ownerDetailsResponse == null)
                throw new IllegalArgumentException("User Not Found with the ID " +
                        propertyRegistrationRequest.getUserID());
        } catch(Exception e){
            logger.error(String.format("The invoked error is %s", e));
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body("Error in adding new property");
        }

        LinkedMultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        String response;
        HttpStatus httpStatus = HttpStatus.CREATED;

        try {
            for (MultipartFile file : files) {
                if (!file.isEmpty()) {
                    map.add("imageData", new MultipartInputStreamFileResource(file.getInputStream(), file.getOriginalFilename()));
                }
            }
            propertyRegistrationRequest.setUserName(ownerDetailsResponse.getOwnerName());
            map.add("propertyData", propertyRegistrationRequest);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.MULTIPART_FORM_DATA);

            String url = "http://properties-service/api/property/addNewProperty";

            HttpEntity<LinkedMultiValueMap<String, Object>> requestEntity = new HttpEntity<>(map, headers);
            RestTemplate restTemplate = new RestTemplate();
            response = restTemplate.postForObject(url, requestEntity, String.class);

        } catch (HttpStatusCodeException e) {
            httpStatus = HttpStatus.valueOf(e.getStatusCode().value());
            response = e.getResponseBodyAsString();
            return ResponseEntity
                    .status(httpStatus)
                    .body(response);
        } catch (Exception e) {
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            response = e.getMessage();
            return ResponseEntity
                    .status(httpStatus)
                    .body(response);
        }

        MultiValueMap<String, String> propIdDetails = new LinkedMultiValueMap<>();

        propIdDetails.add("propId", response);
        propIdDetails.add("userId", propertyRegistrationRequest.getUserID());

        WebClient userClient = WebClient.create("http://user-service/api/user/addNewProperty");
        String userPropUpdate = userClient.post()
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(BodyInserters.fromFormData(propIdDetails))
                .retrieve()
                .onStatus(HttpStatus.NOT_FOUND::equals, clientResponse -> Mono.empty())
                .bodyToMono(String.class)
                .block();

        try{
            if(userPropUpdate == null)
                throw new IllegalArgumentException("There seems to be a problem with user service");
        } catch(Exception e){
            logger.error(String.format("The invoked error is %s", e));
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body("Error in adding new property");
        }

        return new ResponseEntity<>(response, httpStatus);
    }
}
