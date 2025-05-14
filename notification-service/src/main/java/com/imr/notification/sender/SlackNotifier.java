package com.imr.notification.sender;

import com.imr.notification.model.Notification;
import com.imr.notification.model.NotificationRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class SlackNotifier  implements NotificationSender{
    private final RestTemplate restTemplate = new RestTemplate();
    private static final Logger logger = LoggerFactory.getLogger(EmailNotifier.class);

    private final String slackWebhookUrl = "https://hooks.slack.com/services/XXX/YYY/ZZZ"; // replace

    public void send(NotificationRequest request) {
        logger.info("Sending Slack notification for the incident id {}",request.getIncidentId());

        restTemplate.postForObject(slackWebhookUrl, Map.of("text", request.getMessage()), String.class);
    }


    @Override
    public String getChannel() {
        return "slack";
    }
}
