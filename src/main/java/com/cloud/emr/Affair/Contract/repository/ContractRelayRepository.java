package com.cloud.emr.Affair.Contract.repository;


import com.cloud.emr.Affair.Contract.entity.ContractRelayEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContractRelayRepository extends JpaRepository<ContractRelayEntity, Long> {
    ContractRelayEntity findByPatientEntity_PatientNo(Long patientNo);
}
