package com.imr.notification.sender;

import com.imr.notification.model.Notification;

public interface NotificationSender {
    void send(Notification notification);
    String getChannel(); // e.g. EMAIL, SLACK, etc.
}
