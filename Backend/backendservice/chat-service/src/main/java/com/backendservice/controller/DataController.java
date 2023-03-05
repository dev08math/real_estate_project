package com.backendservice.controller;

import com.backendservice.dto.FetchRequest;
import com.backendservice.services.ChatRoom.ChatRoom;
import com.backendservice.services.ChatUsers.ChatUsers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user/chat")
public class DataController {
    @Autowired
    private ChatUsers chatUsersService;
    @Autowired
    private ChatRoom chatRoomService;

    @GetMapping("/getContactDetails")
    public ResponseEntity<?> getContactDetails(@RequestParam String userName){
        return ResponseEntity.ok(chatUsersService.getContactDetails(userName));
    }

    @GetMapping("/getMessages")
    public ResponseEntity<?> fetchMessages(@RequestBody FetchRequest fetchRequest){
        return ResponseEntity.ok(chatRoomService.fetchMessages(fetchRequest));
    }
}
