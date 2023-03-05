package com.backendservice.security;

import com.backendservice.dto.UserDTOs.UserDetailsResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class CustomUserDetailsServiceImpl implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        WebClient webClient = WebClient.create("http://user-service");
        UserDetailsResponse userDetailsResponse = webClient.get()
                .uri(builder -> builder.path("/api/user/userDetails").queryParam("userName", userName).build())
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .retrieve()
                .onStatus(HttpStatus.NOT_FOUND::equals, clientResponse -> Mono.empty())
                .bodyToMono(UserDetailsResponse.class)
                .block();

        if(userDetailsResponse == null)
            throw new UsernameNotFoundException("User Not Found with username: " + userName);

        List<SimpleGrantedAuthority> authorities = userDetailsResponse.getAuthorities().stream()
                .map(SimpleGrantedAuthority::new).toList();
        return CustomUserDetailsImpl.builder().id(userDetailsResponse.getId())
                .email(userDetailsResponse.getEmail()).displayLink(userDetailsResponse.getDisplayLink())
                .userName(userName).password(userDetailsResponse.getPassword()).authorities(authorities).build();
    }

}
