package com.imr.notification.consumer;

import com.imr.notification.model.NotificationRequest;
import com.imr.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KafkaNotificationListener {

    private final NotificationService notificationService;

    @KafkaListener(topics = "incident-notifications", groupId = "notification-service-group")
    public void listen(NotificationRequest request) {
        notificationService.process(request);
    }
}
