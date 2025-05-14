package com.imr.notification.controller;

import com.imr.notification.model.NotificationRequest;
import com.imr.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/notify")
public class NotificationController {

    private final NotificationService notificationService;

    @PostMapping
    public ResponseEntity<String> notify(@RequestBody NotificationRequest request) {
        notificationService.process(request);
        return ResponseEntity.ok("Notification processed");
    }
}
