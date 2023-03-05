package com.backendservice;

import com.backendservice.filters.RateLimiterFilter;
import com.backendservice.utils.UriKeyResolver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.gateway.filter.ratelimit.RateLimiter;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class ApiGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayApplication.class, args);
    }
    @Bean
    public RateLimiter<?> getRateLimiter(){
        return new RateLimiterFilter();
    }
    @Bean(name = UriKeyResolver.BEAN_NAME)
    public KeyResolver getUriKeyResolver() {
        return new UriKeyResolver();
    }
}