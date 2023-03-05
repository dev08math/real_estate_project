package com.backendservice.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SentMessage {
    private String senderName;
    private String recipientName;
    private String content;
    private String timestamp;
}

