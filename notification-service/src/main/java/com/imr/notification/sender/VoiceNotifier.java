package com.imr.notification.sender;

import com.imr.notification.model.Notification;
import org.springframework.stereotype.Service;

@Service
public class VoiceNotifier implements NotificationSender  {


    @Override
    public void send(Notification notification) {

    }

    @Override
    public String getChannel() {
        return "";
    }
}

