package com.khamidgaipov.api.giybat.uz.serviceImpl;

import com.khamidgaipov.api.giybat.uz.exception.AppBadException;
import com.khamidgaipov.api.giybat.uz.util.JwtUtil;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderServiceImpl {
    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String fromAccount;

    public void sendRegistrationEmail(String email, Long profileId) {
        String subject = "Complete registered";
        String body = "<!DOCTYPE html>\n" +
                "<html lang=\"en\" xmlns=\"http://www.w3.org/1999/html\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Verification Page</title>\n" +
                "  <style>\n" +
                "      a {\n" +
                "          padding: 10px 30px;\n" +
                "          display: inline-block;\n" +
                "      }\n" +
                "      .button-link {\n" +
                "          text-decoration: none;\n" +
                "          color: white;\n" +
                "          background-color: #00a1ec;\n" +
                "      }\n" +
                "      .button-link:hover {\n" +
                "          background-color: #dd4444;\n" +
                "      }\n" +
                "  </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "<h1>Complete Registration</h1>\n" +
                "<p>\n" +
                "    Please click to button for complete registration.\n" +
                "</p>\n" +
                "<a href=\"http://localhost:8080/auth/registration/verification/%s\" target=\"_blank\" class=\"button-link\">Click</a>\n" +
                "</body>\n" +
                "</html>";

        body = String.format(body, JwtUtil.encode(profileId));
        sendMimeMessage(email, subject, body);
    }

    //Only for text mail
    public void sendSimpleEmail(String email, String subject, String body) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(fromAccount);
        mailMessage.setTo(email);
        mailMessage.setSubject(subject);
        mailMessage.setText(body);
        mailSender.send(mailMessage);
    }

    //For HTML
    public void sendMimeMessage(String email, String subject, String body) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            mimeMessage.setFrom(fromAccount);

            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setTo(email);
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(body, true);
            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            throw new AppBadException("Failed to send mime message");
        }


    }
}
