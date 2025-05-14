package com.imr.notification.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String channel;
    private String recipient;
    private String message;

    @Enumerated(EnumType.STRING)
    private NotificationStatus status;

    private int retryCount;
    private LocalDateTime createdAt;
    private LocalDateTime lastTriedAt;
}
