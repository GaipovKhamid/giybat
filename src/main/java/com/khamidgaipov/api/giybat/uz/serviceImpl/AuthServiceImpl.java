package com.khamidgaipov.api.giybat.uz.serviceImpl;

import com.khamidgaipov.api.giybat.uz.dto.RegistrationDto;
import com.khamidgaipov.api.giybat.uz.entity.ProfileEntity;
import com.khamidgaipov.api.giybat.uz.enums.GeneralStatus;
import com.khamidgaipov.api.giybat.uz.enums.ProfileRole;
import com.khamidgaipov.api.giybat.uz.exception.AppBadException;
import com.khamidgaipov.api.giybat.uz.repository.ProfileRepository;
import com.khamidgaipov.api.giybat.uz.util.JwtUtil;
import io.jsonwebtoken.JwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class AuthServiceImpl {
    @Autowired
    ProfileRepository profileRepository;
    @Autowired
    BCryptPasswordEncoder bc;
    @Autowired
    ProfileRoleServiceImpl profileRoleService;
    @Autowired
    EmailSenderServiceImpl senderService;
    @Autowired
    ProfileServiceImpl profileService;


    public String registration(RegistrationDto dto) {
        Optional<ProfileEntity> optional = profileRepository.findByUsernameAndVisibleTrue(dto.getUsername());
        if (optional.isPresent()) {
            ProfileEntity entity = optional.get();
            if (entity.getStatus().equals(GeneralStatus.IN_REGISTRATION)) {
                profileRoleService.deleteRoles(entity.getId());
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
        senderService.sendRegistrationEmail(dto.getUsername(), entity.getId());
        return "Successfully registered";
    }

    public String regVerification(String token) {
        Long profileId = JwtUtil.decodeRegVerToken(token);
        try {
            ProfileEntity profile = profileService.getById(profileId);
            if (profile.getStatus().equals(GeneralStatus.IN_REGISTRATION)) {
                profileRepository.changeStatus(profileId, GeneralStatus.ACTIVE);
                return "Successfully activated";
            }
        } catch (JwtException e) {
        }

        throw new AppBadException("Registration failed");
    }
}
