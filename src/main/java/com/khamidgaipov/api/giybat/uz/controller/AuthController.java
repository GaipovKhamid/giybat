package com.khamidgaipov.api.giybat.uz.controller;

import com.khamidgaipov.api.giybat.uz.dto.RegistrationDto;
import com.khamidgaipov.api.giybat.uz.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    AuthService authService;

    @PostMapping("/registration")
    public ResponseEntity<String> registration(@Valid @RequestBody RegistrationDto dto) {
        return ResponseEntity.ok().body(authService.registration(dto));
    }

    @GetMapping("/registration/verification/{id}")
    public ResponseEntity<String> regVerification(@PathVariable("id") Long profileId) {
        return ResponseEntity.ok().body(authService.regVerification (profileId));
    }
}
