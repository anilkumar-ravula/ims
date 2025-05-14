package com.imr.notification.sender;

import com.imr.notification.model.Notification;
import com.imr.notification.model.NotificationRequest;

public interface NotificationSender {
    void send(NotificationRequest notification);
    String getChannel(); // e.g. EMAIL, SLACK, etc.
}
