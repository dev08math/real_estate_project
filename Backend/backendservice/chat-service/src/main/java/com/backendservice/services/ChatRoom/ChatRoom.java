package com.backendservice.services.ChatRoom;

import com.backendservice.dto.FetchRequest;
import com.backendservice.dto.MessagesResponse;
import com.backendservice.dto.SentMessage;

import java.util.List;

public interface ChatRoom {
    void addMessage(SentMessage sentMessage);
    List<MessagesResponse> fetchMessages(FetchRequest fetchRequest);
}
