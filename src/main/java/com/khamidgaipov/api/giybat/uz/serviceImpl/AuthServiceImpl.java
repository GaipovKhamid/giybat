package com.khamidgaipov.api.giybat.uz.serviceImpl;

import com.khamidgaipov.api.giybat.uz.dto.RegistrationDto;
import com.khamidgaipov.api.giybat.uz.entity.ProfileEntity;
import com.khamidgaipov.api.giybat.uz.enums.GeneralStatus;
import com.khamidgaipov.api.giybat.uz.enums.ProfileRole;
import com.khamidgaipov.api.giybat.uz.exception.AppBadException;
import com.khamidgaipov.api.giybat.uz.repository.ProfileRepository;
import com.khamidgaipov.api.giybat.uz.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    ProfileRepository profileRepository;
    @Autowired
    BCryptPasswordEncoder bc;
    @Autowired
    ProfileRoleServiceImpl profileRoleService;

    @Override
    public String registration(RegistrationDto dto) {
        Optional<ProfileEntity> optional = profileRepository.findByUsernameAndVisibleTrue(dto.getUsername());
        if (optional.isPresent()) {
            ProfileEntity entity = optional.get();
            if (entity.getStatus().equals(GeneralStatus.IN_REGISTRATION)) {
                profileRepository.delete(entity);
                // sms todo
            } else {
                throw new AppBadException("Username already exists");
            }
        }

        ProfileEntity entity = new ProfileEntity();
        entity.setName(dto.getName());
        entity.setUsername(dto.getUsername());
        entity.setPassword(bc.encode(dto.getPassword()));
        entity.setStatus(GeneralStatus.IN_REGISTRATION);
        entity.setVisible(true);
        entity.setCreatedDate(LocalDateTime.now());
        profileRepository.save(entity);

        profileRoleService.create(entity.getId(), ProfileRole.ROLE_USER);
        return "Successfully registered";
    }
}
