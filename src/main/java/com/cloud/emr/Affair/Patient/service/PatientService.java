package com.cloud.emr.Affair.Patient.service;

import com.cloud.emr.Affair.Patient.entity.PatientEntity;
import com.cloud.emr.Affair.Patient.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {

    private static PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public static List<PatientEntity> searchPatientByName(String patientName) {
        if(patientName == null || patientName.isEmpty()) {
            throw new IllegalArgumentException("환자 이름은 필수입니다.");
        }

        return patientRepository.findByPatientName(patientName);
    }

    public PatientEntity findPatientByNo(String patientNo) {
        return patientRepository.findByPatientNo(patientNo)
                .orElseThrow(() -> new IllegalArgumentException("해당 환자를 찾을 수 없습니다." + patientNo));
    }
}
