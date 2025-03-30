package com.khamidgaipov.api.giybat.uz.entity;

import com.khamidgaipov.api.giybat.uz.enums.GeneralStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "profile")
@Getter
@Setter
public class ProfileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    @Column
    private String username; // email or phone num

    @Column
    private String password;

    @Enumerated(EnumType.STRING)
    @Column
    private GeneralStatus status;

    @Column
    private Boolean visible  = Boolean.TRUE;

    @Column
    private LocalDateTime createdDate;

}
