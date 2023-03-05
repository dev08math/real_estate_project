package com.backendservice.dto;

import lombok.Data;

@Data
public class FetchRequest {
    String senderName;
    String receiverName;
    Integer limit;
}
