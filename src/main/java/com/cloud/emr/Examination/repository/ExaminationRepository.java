package com.cloud.emr.Examination.repository;

import com.cloud.emr.Examination.entity.ExaminationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExaminationRepository extends JpaRepository<ExaminationEntity, Long> {
    ExaminationEntity findByExaminationName(String examinationName);
}
