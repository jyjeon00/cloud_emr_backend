package com.cloud.emr.Affair.MedicalFee.repository;

import com.cloud.emr.Affair.MedicalFee.entity.MedicalFeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicalFeeRepository extends JpaRepository <MedicalFeeEntity, Long> {
}
