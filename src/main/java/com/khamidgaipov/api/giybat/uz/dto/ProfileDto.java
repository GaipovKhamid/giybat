package com.khamidgaipov.api.giybat.uz.dto;

import com.khamidgaipov.api.giybat.uz.enums.GeneralStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfileDto {
    private long id;
    private String name;
    private String username;
    private String password;
    private GeneralStatus status;
    private boolean visible;
}
