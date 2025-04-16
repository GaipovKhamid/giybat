package com.khamidgaipov.api.giybat.uz.serviceImpl;

import com.khamidgaipov.api.giybat.uz.entity.ProfileRoleEntity;
import com.khamidgaipov.api.giybat.uz.enums.ProfileRole;
import com.khamidgaipov.api.giybat.uz.repository.ProfileRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ProfileRoleServiceImpl {
    @Autowired
    private ProfileRoleRepository repository;

    public void create(Long prId, ProfileRole role) {
        ProfileRoleEntity profileRoleEntity = new ProfileRoleEntity();
        profileRoleEntity.setProfileId(prId);
        profileRoleEntity.setRoles(role);
        profileRoleEntity.setCreatedDate(LocalDateTime.now());

        repository.save(profileRoleEntity);
    }

    public void deleteRoles(Long id){
        repository.deleteByProfileId(id);
    }
}
