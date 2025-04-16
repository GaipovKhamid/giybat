package com.khamidgaipov.api.giybat.uz.repository;

import com.khamidgaipov.api.giybat.uz.entity.ProfileRoleEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;

public interface ProfileRoleRepository extends CrudRepository<ProfileRoleEntity, Long> {
    @Transactional
    @Modifying
    void deleteByProfileId(Long profileId);
}
