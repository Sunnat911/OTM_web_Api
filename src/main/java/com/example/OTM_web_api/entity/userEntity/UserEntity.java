package com.example.OTM_web_api.entity.userEntity;



import com.example.OTM_web_api.entity.baseEntity.BaseEntity;
import com.example.OTM_web_api.entity.roleEntity.RoleEntity;
import com.example.OTM_web_api.entity.univerEntity.UniverEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class UserEntity extends BaseEntity implements UserDetails {

    private String firstName;
    private String userName;
    private String password;
    private String phoneNumber;

    private String passportNumber;


    @ManyToOne(fetch = FetchType.LAZY)
    private UniverEntity univerEntity;

    @ManyToMany
    private List<RoleEntity> roleEntityList;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roleEntityList;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}



