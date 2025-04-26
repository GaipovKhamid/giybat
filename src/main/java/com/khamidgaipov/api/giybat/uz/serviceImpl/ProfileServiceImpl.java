package com.khamidgaipov.api.giybat.uz.serviceImpl;

import com.khamidgaipov.api.giybat.uz.entity.ProfileEntity;
import com.khamidgaipov.api.giybat.uz.exception.AppBadException;
import com.khamidgaipov.api.giybat.uz.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileServiceImpl {
    @Autowired
    private ProfileRepository repository;


    public ProfileEntity getById(Long id) {
        return repository.findByIdAndVisibleTrue(id).orElseThrow( () -> new AppBadException("Profile not found"));
    }
}
