package com.khamidgaipov.api.giybat.uz.serviceImpl;

import com.khamidgaipov.api.giybat.uz.entity.ProfileEntity;
import com.khamidgaipov.api.giybat.uz.exception.AppBadException;
import com.khamidgaipov.api.giybat.uz.repository.ProfileRepository;
import com.khamidgaipov.api.giybat.uz.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;

import java.util.Optional;

public class ProfileServiceImpl implements ProfileService {
    @Autowired
    private ProfileRepository repository;


    @Override
    public ProfileEntity getById(Long id) {
        return repository.findByIdAndVisibleTrue(id).orElseThrow( () -> {
            throw new AppBadException("Profile not found");
        });
    }
}
