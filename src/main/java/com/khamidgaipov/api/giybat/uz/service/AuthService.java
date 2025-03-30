package com.khamidgaipov.api.giybat.uz.service;

import com.khamidgaipov.api.giybat.uz.dto.RegistrationDto;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {
    String registration(RegistrationDto dto);
}
