package com.backendservice.events;

import com.backendservice.models.UserCollection;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.context.ApplicationEvent;

@Data
@EqualsAndHashCode(callSuper = false)
public class RegistrationEvent extends ApplicationEvent {
    private UserCollection user;
    private String applicationUrl;

    public RegistrationEvent(UserCollection user, String applicationUrl){
        super(user);
        this.user = user;
        this.applicationUrl = applicationUrl;
    }
}
