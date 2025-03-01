package com.cloud.emr.Examination.Equipment.repository;

import com.cloud.emr.Examination.Equipment.entity.EquipmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EquipmentRepository extends JpaRepository<EquipmentEntity, Long> {
    EquipmentEntity findByEquipmentId(Long equipmentId);
}
