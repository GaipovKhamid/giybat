package com.khamidgaipov.api.giybat.uz.service;

import org.springframework.stereotype.Service;

@Service
public interface EmailSenderService {
    void sendEmail(String email, String subject,String body);
    void sendRegistrationEmail(String email, Long profileId);
}
