package com.backendservice.services.ChatUsers;

import com.backendservice.dto.SentMessage;
import com.backendservice.dto.UserDetailsResponse;
import com.backendservice.models.ChatsUsersCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.HashMap;
import java.util.List;

@Service
public class ChatUsersImpl implements ChatUsers {
    @Autowired
    private  WebClient.Builder webClientBuilder;
    @Autowired
    private MongoTemplate mt;
    private ChatsUsersCollection addNewUser(String userName){
        UserDetailsResponse userDetailsResponse = webClientBuilder.build().get()
                .uri("http://user-service/user/getChatUserDetails",
                        uriBuilder -> uriBuilder.queryParam("userName", userName).build())
                .retrieve()
                .bodyToMono(UserDetailsResponse.class)
                .block();
        if(userDetailsResponse == null) throw new IllegalArgumentException(
                String.format("Can't find a user with userName  %s in DB", userName));

        ChatsUsersCollection user = new ChatsUsersCollection();
        user.setUserName(userDetailsResponse.getUserName());
        user.setDisplayLink(userDetailsResponse.getDisplayLink());
        return user;
    }
    @Override
    public void validateMessage(SentMessage sentMessage) {
        String sender = sentMessage.getSenderName(), receiver = sentMessage.getRecipientName();
        Query qs = new Query().addCriteria(Criteria.where("userName").is(sender)),
                qr = new Query().addCriteria(Criteria.where("userName").is(receiver));

        ChatsUsersCollection senderObj = mt.findOne(qs, ChatsUsersCollection.class, "ChatsUsersCollection");
        if(senderObj == null) senderObj = addNewUser(sender);

        ChatsUsersCollection receiverObj = mt.findOne(qr, ChatsUsersCollection.class);
        if(receiverObj == null) receiverObj = addNewUser(receiver);

        HashMap<String, String> senderContacts = new HashMap<>(senderObj.getContacts());
        senderContacts.put(receiverObj.getUserName(), receiverObj.getDisplayLink());
        senderObj.setContacts(senderContacts);
        mt.save(senderObj);

        HashMap<String, String> receiverContacts = new HashMap<>(receiverObj.getContacts());
        receiverContacts.put(senderObj.getUserName(), senderObj.getDisplayLink());
        receiverObj.setContacts(receiverContacts);
        mt.save(receiverObj);
    }

    @Override
    public List<UserDetailsResponse> getContactDetails(String userName) {
        Query query = new Query().addCriteria(Criteria.where("userName").is(userName));
        ChatsUsersCollection user = mt.findOne(query, ChatsUsersCollection.class, "ChatsUsersCollection");
        if(user == null) throw new IllegalArgumentException(
                String.format("Can't find the %s's name in Chat's DB", userName));
        return user.getContacts().entrySet().stream()
                                .map(contact -> UserDetailsResponse.builder()
                                                                   .userName(contact.getKey())
                                                                   .displayLink(contact.getValue())
                                                                   .build()).toList();
    }
}
