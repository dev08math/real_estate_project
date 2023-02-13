package com.chatservice.chatserver.service;

import com.chatservice.chatserver.collections.ChatUsersCollection;
import com.chatservice.chatserver.collections.ChatsCollection;
import com.chatservice.chatserver.dto.SentMessage;
import com.chatservice.chatserver.dto.UserDetailsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.HashSet;

@Service
public class ChatRoomImpl implements ChatRoom {
    @Autowired
    private  WebClient.Builder webClientBuilder;
    @Autowired
    private MongoTemplate mt;

    private void addNewUser(String userName, String contactName){
        UserDetailsResponse userDetailsResponse = webClientBuilder.build().get()
                .uri("http://localhost:8060/api/user/getUserDetails",
                        uriBuilder -> uriBuilder.queryParam("userName", userName).build())
                .retrieve()
                .bodyToMono(UserDetailsResponse.class)
                .block();
        if(userDetailsResponse == null) throw new IllegalArgumentException(
                String.format("Can't find the %s's name in DB", userName));

        ChatUsersCollection user = new ChatUsersCollection();
        user.setName(userDetailsResponse.getName());
        user.setDp(userDetailsResponse.getDp());
        HashSet<String> contacts = new HashSet<>();
        contacts.add(contactName);
        user.setContacts(contacts);
    }
    private void validateMessage(SentMessage sentMessage) throws IllegalArgumentException {
        String sender = sentMessage.getSenderName(), receiver = sentMessage.getRecipientName();
        Query qs = new Query().addCriteria(Criteria.where("name").is(sender)),
              qr = new Query().addCriteria(Criteria.where("name").is(receiver));

        ChatUsersCollection senderObj = mt.findOne(qs, ChatUsersCollection.class);
        if(senderObj == null) addNewUser(sender, receiver);
        else senderObj.getContacts().add(receiver);

        ChatUsersCollection receiverObj = mt.findOne(qr, ChatUsersCollection.class);
        if(receiverObj == null) addNewUser(receiver, sender);
        else receiverObj.getContacts().add(sender);
    }

    private String getIdentifier(SentMessage sentMessage) {
        StringBuilder stringBuilder = new StringBuilder();
        String s1 = sentMessage.getRecipientName(), s2 = sentMessage.getSenderName();
        if(s1.length() < s2.length()){
            stringBuilder.append(s1).append(s1.length())
                         .append(s2).append(s2.length());
        }
        else{
            stringBuilder.append(s2).append(s2.length())
                    .append(s1).append(s1.length());
        }
        return stringBuilder.toString();
    }
    @Override
    public void addMessage(SentMessage sentMessage){
        validateMessage(sentMessage);
        String identifier = getIdentifier(sentMessage);
        ChatsCollection chat = new ChatsCollection();
        chat.setContent(sentMessage.getContent());
        chat.setSenderName(sentMessage.getSenderName());
        chat.setRecipientName(sentMessage.getRecipientName());
        chat.setIdentifier(identifier);
        chat.setTimestamp(sentMessage.getTimestamp());
        mt.save(chat);
    }



}
