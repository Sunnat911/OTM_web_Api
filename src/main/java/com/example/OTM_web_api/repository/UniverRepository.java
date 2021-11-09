package com.example.OTM_web_api.repository;

import com.example.OTM_web_api.entity.univerEntity.UniverEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
 public interface UniverRepository extends JpaRepository<UniverEntity,Long> {
 Optional<UniverEntity> findByUniverName(String univerName);

}
