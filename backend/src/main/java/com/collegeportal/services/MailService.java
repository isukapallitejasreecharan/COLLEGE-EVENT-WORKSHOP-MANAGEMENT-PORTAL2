package com.collegeportal.services;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(MailService.class);
    private final JavaMailSender mailSender;

    public void sendMail(String to, String subject, String body) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setSubject(subject);
            message.setText(body);
            mailSender.send(message);
        } catch (Exception ex) {
            log.warn("Email delivery skipped for {} due to {}. Body: {}", to, ex.getMessage(), body);
        }
    }
    public MailService(final JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }
}

