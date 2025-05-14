package com.imr.notification.sender;

import com.imr.notification.model.Notification;
import com.imr.notification.model.NotificationRequest;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailNotifier implements NotificationSender {
    private final JavaMailSender mailSender;
    private static final Logger logger = LoggerFactory.getLogger(EmailNotifier.class);

    public void send(NotificationRequest request) {
        logger.info("Sending email notification for the incident id {}",request.getIncidentId());
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(request.getRecipient());
            helper.setSubject("Incident Notification");
            helper.setText(request.getMessage(), true);
            mailSender.send(message);
        } catch (MessagingException e) {
            logger.error("Failed to send message ", e);
            throw new RuntimeException("Failed to send email", e);
        }
    }

    @Override
    public String getChannel() {
        return "email";
    }
}

