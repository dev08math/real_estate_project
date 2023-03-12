package com.backendservice.services.ChatRoom;

import com.backendservice.dto.FetchRequest;
import com.backendservice.dto.MessagesResponse;
import com.backendservice.dto.SentMessage;
import com.backendservice.models.ChatsCollection;
import com.backendservice.services.ChatUsers.ChatUsers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class ChatRoomImpl implements ChatRoom {
    @Autowired
    private MongoTemplate mt;
    @Autowired
    private ChatUsers chatUsersService;
    private String getIdentifier(String senderName, String recipientName) {
        StringBuilder stringBuilder = new StringBuilder();
        if(senderName.length() < recipientName.length()){
            stringBuilder.append(senderName).append(senderName.length())
                         .append(recipientName).append(recipientName.length());
        }
        else{
            stringBuilder.append(recipientName).append(recipientName.length())
                    .append(senderName).append(senderName.length());
        }
        return stringBuilder.toString();
    }
    @Override
    @Async
    public void addMessage(SentMessage sentMessage){
        chatUsersService.validateMessage(sentMessage);
        String identifier = getIdentifier(sentMessage.getSenderName(), sentMessage.getRecipientName());
        ChatsCollection chat = new ChatsCollection();
        chat.setContent(sentMessage.getContent());
        chat.setSenderName(sentMessage.getSenderName());
        chat.setRecipientName(sentMessage.getRecipientName());
        chat.setIdentifier(identifier);
        chat.setTimestamp(sentMessage.getTimestamp());
        mt.save(chat);
    }

    @Override
    public List<MessagesResponse> fetchMessages(FetchRequest fetchRequest) {
        String identifier = getIdentifier(fetchRequest.getSenderName(), fetchRequest.getReceiverName());
        List<MessagesResponse> messages = new ArrayList<>();
        Query query = new Query().addCriteria(Criteria.where("identifier").is(identifier))
                                 .limit(fetchRequest.getLimit());
        List<ChatsCollection> chats = mt.find(query, ChatsCollection.class, "ChatsCollection");
        for(ChatsCollection c : chats){
            messages.add(MessagesResponse.builder().content(c.getContent())
                                                   .senderName(c.getSenderName())
                                                   .recipientName(c.getRecipientName())
                                                   .timeStamp(c.getTimestamp()).build());
        }
        return messages;
    }

}
