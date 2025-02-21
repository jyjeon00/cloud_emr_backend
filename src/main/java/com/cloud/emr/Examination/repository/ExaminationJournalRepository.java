package com.cloud.emr.Examination.repository;

import com.cloud.emr.Affair.Patient.entity.PatientEntity;
import com.cloud.emr.Examination.entity.ExaminationJournalEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExaminationJournalRepository extends JpaRepository<ExaminationJournalEntity, Long> {
    List<ExaminationJournalEntity> findExaminationJournalEntitiesByPatientEntity(PatientEntity patientEntity);
}
