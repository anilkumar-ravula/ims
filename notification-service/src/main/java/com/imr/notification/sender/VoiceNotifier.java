package com.imr.notification.sender;

import com.imr.notification.model.NotificationRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class VoiceNotifier implements NotificationSender  {
    private static final Logger logger = LoggerFactory.getLogger(EmailNotifier.class);


    @Override
    public void send(NotificationRequest notification) {
        logger.info("Sending Slack notification for the incident id {}",notification.getIncidentId());
    }

    @Override
    public String getChannel() {
        return "voice";
    }
}

