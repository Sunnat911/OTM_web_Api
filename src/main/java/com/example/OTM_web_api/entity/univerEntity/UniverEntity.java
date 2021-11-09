package com.example.OTM_web_api.entity.univerEntity;


import com.example.OTM_web_api.entity.baseEntity.BaseEntity;;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class UniverEntity extends BaseEntity {

    @Column(unique = true)
    private String univerName;
//
//    @JsonIgnore
//    @OneToMany(mappedBy = "univerEntity",fetch = FetchType.LAZY)
//    private List<UserEntity> userEntity;





}
