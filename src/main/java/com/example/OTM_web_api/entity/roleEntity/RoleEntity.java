package com.example.OTM_web_api.entity.roleEntity;

import com.example.OTM_web_api.entity.Enum.roleEnum.RoleEnum;
import com.example.OTM_web_api.entity.baseEntity.BaseEntity;
import com.example.OTM_web_api.entity.userEntity.UserEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToMany;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class RoleEntity extends BaseEntity implements GrantedAuthority {



    @Enumerated(EnumType.STRING)
    private RoleEnum roleEnum;


    @ManyToMany
    private List<UserEntity> userEntity;


    @Override
    public String getAuthority() {
        return roleEnum.name();
    }

    public RoleEntity(RoleEnum roleEnum) {
        this.roleEnum = roleEnum;
    }
}
