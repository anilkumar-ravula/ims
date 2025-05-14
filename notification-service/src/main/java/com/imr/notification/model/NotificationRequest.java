package com.imr.notification.model;

import lombok.Data;

@Data
public class NotificationRequest {
    private String channel;
    private String recipient;
    private String message;
}
