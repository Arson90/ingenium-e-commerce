package com.ingenium.ingeniumecommerce.util.mail;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class EmailTestController {
    private final EmailService emailService;
    @GetMapping("/ingenium/send_notification")
    ResponseEntity<Void> sendNotification() {
        EmailDetails details = new EmailDetails("arek1990cda@gmail.com", "Body test", "Test", null);
        emailService.sendSimpleMail(details);
        return ResponseEntity.ok().build();
    }
}
