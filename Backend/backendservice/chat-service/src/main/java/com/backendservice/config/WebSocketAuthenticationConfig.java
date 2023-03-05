package com.backendservice.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Objects;

@Configuration
@EnableWebSocketMessageBroker
@Order(Ordered.HIGHEST_PRECEDENCE + 99)
public class WebSocketAuthenticationConfig implements WebSocketMessageBrokerConfigurer {

    private static final Logger logger = LoggerFactory.getLogger(WebSocketAuthenticationConfig.class);

    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.interceptors(new ChannelInterceptor() {
            @Override
            public Message<?> preSend(Message<?> message, MessageChannel channel) {
                StompHeaderAccessor accessor =
                        MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
                if (StompCommand.CONNECT.equals(Objects.requireNonNull(accessor).getCommand())) {
                    List<String> authorization = accessor.getNativeHeader("X-Authorization");
                    if(authorization == null) throw new IllegalArgumentException("Invalid tokens");
                    logger.debug("X-Authorization: {}", authorization);
                    String accessToken = authorization.get(0).split(" ")[1];

                    WebClient webClient = WebClient.create("http://localhost:8061");
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = webClient.get()
                            .uri("/api/public/auth/chat/login")
                            .accept(MediaType.APPLICATION_JSON)
                            .header("Authorization", accessToken)
                            .retrieve()
                            .onStatus(HttpStatus.NOT_FOUND::equals, clientResponse -> Mono.empty())
                            .bodyToMono(UsernamePasswordAuthenticationToken.class)
                            .block();
                    if(usernamePasswordAuthenticationToken == null)
                        throw new SecurityException("Unable to connect with the server");
                    accessor.setUser(usernamePasswordAuthenticationToken);
                }
                return message;
            }
        });
    }
}
