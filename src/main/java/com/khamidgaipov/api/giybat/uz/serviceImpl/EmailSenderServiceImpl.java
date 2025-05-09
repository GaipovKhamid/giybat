package com.khamidgaipov.api.giybat.uz.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderServiceImpl {
    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String fromAccount;

    public void sendRegistrationEmail(String email, Long profileId) {
        String subject = "Complete registered";
        String body = "Please click to link for complete http://localhost:8080/auth/registration/verification/"+ profileId;
        sendEmail(email, subject, body);
    }

    public void sendEmail(String email, String subject, String body) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(fromAccount);
        mailMessage.setTo(email);
        mailMessage.setSubject(subject);
        mailMessage.setText(body);
        mailSender.send(mailMessage);
    }
}
