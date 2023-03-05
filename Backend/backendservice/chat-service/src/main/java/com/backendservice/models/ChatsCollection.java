package com.backendservice.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@Document(collection = "ChatMessagesCollection")
public class ChatsCollection {
    @Id
    private String chatId;
    private String identifier;
    private String senderName;
    private String recipientName;
    private String content;
    private String timestamp;
}
