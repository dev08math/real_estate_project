package com.backend.realestatebackend.events;

import org.springframework.context.ApplicationEvent;

import com.backend.realestatebackend.collections.UserCollection;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class RegistrationEvent extends ApplicationEvent{
    private UserCollection user;
    private String applicationUrl;

    public RegistrationEvent(UserCollection user, String applicationUrl){
        super(user);
        this.user = user;
        this.applicationUrl = applicationUrl;
    }
}
