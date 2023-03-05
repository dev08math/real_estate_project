package com.backendservice.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ChatUserDetailsResponse {
    private String displayLink;
    private String userName;
}
