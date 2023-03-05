package com.backendservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MessagesResponse {
    private String senderName;
    private String recipientName;
    private String content;
    private String timeStamp;
}
