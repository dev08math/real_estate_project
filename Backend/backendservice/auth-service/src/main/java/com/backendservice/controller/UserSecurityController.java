package com.backendservice.controller;

import com.github.fge.jsonpatch.JsonPatch;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/secure/user")
public class UserSecurityController {
    @PatchMapping("/update")
    public String updateUserDetails(@RequestBody JsonPatch patch, @RequestParam("userName") String userName){

        WebClient webClient = WebClient.create("http://user-service");
        String patchResponse = webClient.patch()
                .uri(builder -> builder.path("/api/user/updateUserDetails").queryParam("userName", userName).build())
                .body(Mono.just(patch), JsonPatch.class)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .retrieve()
                .onStatus(HttpStatus.NOT_FOUND::equals, clientResponse -> Mono.empty())
                .bodyToMono(String.class)
                .block();

        if(patchResponse == null) return "Unable to update user details";
        return patchResponse;
    }
}
