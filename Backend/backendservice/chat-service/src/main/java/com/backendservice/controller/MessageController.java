package com.backendservice.controller;

import com.backendservice.dto.ReceivingMessage;
import com.backendservice.dto.SentMessage;
import com.backendservice.services.ChatRoom.ChatRoom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Controller;

@Controller
@EnableAsync
public class MessageController {
    @Autowired
    SimpMessagingTemplate messagingTemplate;
    @Autowired
    private ChatRoom chatRoomService;

    @MessageMapping("/chat")
    public void processMessage(@Payload SentMessage sentMessage){

        chatRoomService.addMessage(sentMessage);
        messagingTemplate.convertAndSendToUser(sentMessage.getRecipientName(),
                                     "/queue/messages",
                                               new ReceivingMessage(sentMessage.getContent(),
                                                                    sentMessage.getSenderName()));
    }
}
