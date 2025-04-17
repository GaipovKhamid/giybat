package com.khamidgaipov.api.giybat.uz.repository;

import com.khamidgaipov.api.giybat.uz.entity.ProfileEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ProfileRepository extends CrudRepository<ProfileEntity, Long> {
    Optional<ProfileEntity> findByUsernameAndVisibleTrue(String username);
    Optional<ProfileEntity> findByIdAndVisibleTrue(Long id);
}
