package com.imr.notification.service;

import com.imr.notification.model.Notification;
import com.imr.notification.model.NotificationRequest;
import com.imr.notification.model.NotificationStatus;
import com.imr.notification.repository.NotificationRepository;
import com.imr.notification.sender.NotificationSender;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository repository;
    private final List<NotificationSender> senders;

    public void process(NotificationRequest request) {
        Notification notification = Notification.builder()
                .channel(request.getChannel())
                .recipient(request.getRecipient())
                .message(request.getMessage())
                .status(NotificationStatus.PENDING)
                .createdAt(LocalDateTime.now())
                .build();

        repository.save(notification);

        Optional<NotificationSender> senderOpt = senders.stream()
                .filter(s -> s.getChannel().equalsIgnoreCase(request.getChannel()))
                .findFirst();

        try {
            if (senderOpt.isPresent()) {
                senderOpt.get().send(notification);
                notification.setStatus(NotificationStatus.SENT);
            } else {
                throw new IllegalArgumentException("Unsupported channel");
            }
        } catch (Exception e) {
            notification.setStatus(NotificationStatus.FAILED);
        }

        notification.setLastTriedAt(LocalDateTime.now());
        repository.save(notification);
    }
}
