package com.cloud.emr.Affair.MedicalFee.service;

import com.cloud.emr.Affair.MedicalFee.entity.MedicalTypeEntity;
import com.cloud.emr.Affair.MedicalFee.repository.MedicalTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedicalTypeService {

    @Autowired
    private MedicalTypeRepository medicalTypeRepository;




    public MedicalTypeEntity findMedicalTypeById(Long medicalTypeId) {
        return medicalTypeRepository.findById(medicalTypeId)
                .orElseThrow(() -> new IllegalArgumentException("해당 유형 찾을 수 없음" + medicalTypeId));
    }
}
