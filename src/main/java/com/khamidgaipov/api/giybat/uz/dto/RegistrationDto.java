package com.khamidgaipov.api.giybat.uz.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistrationDto {
    @NotBlank(message = "name required")

    private String name;
    @NotBlank(message = "username required")

    private String username;
    @NotBlank(message = "password required")

    private String password;
}
