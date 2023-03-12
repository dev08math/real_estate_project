package com.backendservice.services.ChatUsers;

import com.backendservice.dto.SentMessage;
import com.backendservice.dto.UserDetailsResponse;

import java.util.List;

public interface ChatUsers {
    void validateMessage(SentMessage sentMessage);
    List<UserDetailsResponse> getContactDetails(String userName);
}
