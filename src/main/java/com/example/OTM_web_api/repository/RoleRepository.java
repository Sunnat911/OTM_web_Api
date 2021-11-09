package com.example.OTM_web_api.repository;

import com.example.OTM_web_api.entity.Enum.roleEnum.RoleEnum;
import com.example.OTM_web_api.entity.roleEntity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository <RoleEntity,Long> {
    RoleEntity findByRoleEnum(RoleEnum roleEnumList);
}
