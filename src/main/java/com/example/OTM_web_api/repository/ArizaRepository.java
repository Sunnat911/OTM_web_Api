package com.example.OTM_web_api.repository;

import com.example.OTM_web_api.entity.arizaEntity.ArizaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArizaRepository  extends JpaRepository<ArizaEntity,Long> {
}
