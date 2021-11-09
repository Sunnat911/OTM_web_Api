package com.example.OTM_web_api.entity.arizaEntity;

import com.example.OTM_web_api.entity.Enum.univerEnum.ArizaStateEnum;
import com.example.OTM_web_api.entity.baseEntity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ArizaEntity extends BaseEntity {
    private Long univerId;
    private Long userId;
    private String text;
    @Enumerated(EnumType.STRING)
    private ArizaStateEnum status;

}
