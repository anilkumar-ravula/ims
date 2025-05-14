package com.imr.notification.sender;

import com.imr.notification.model.Notification;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class SlackNotifier  implements NotificationSender{
    private final RestTemplate restTemplate = new RestTemplate();
    private final String slackWebhookUrl = "https://hooks.slack.com/services/XXX/YYY/ZZZ"; // replace

    public void send(String message) {
        restTemplate.postForObject(slackWebhookUrl, Map.of("text", message), String.class);
    }

    @Override
    public void send(Notification notification) {

    }

    @Override
    public String getChannel() {
        return "";
    }
}
