package controller;

import model.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import service.EmailService;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/api/email")
public class EmailController {
    @Autowired
    private EmailService emailService;
    @PostMapping("/sendEmail")
    public ResponseEntity<?> addProperty(@RequestBody Email email){

        boolean result = emailService.sendSimpleMessage(email);
        if(!result) return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Unable to send the email");
        return ResponseEntity.ok("Successfully sent the email");
    }
}
