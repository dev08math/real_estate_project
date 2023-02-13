package com.chatservice.chatserver.service;

import com.chatservice.chatserver.collections.ChatsCollection;
import com.chatservice.chatserver.dto.SentMessage;

public interface ChatRoom {
    void addMessage(SentMessage sentMessage);
}
