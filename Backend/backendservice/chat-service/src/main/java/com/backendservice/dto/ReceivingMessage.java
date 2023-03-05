package com.backendservice.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ReceivingMessage {

    public ReceivingMessage(String content, String senderName){
        this.content = content;
        this.senderName = senderName;
    }
    private String content;
    private String senderName;
}
