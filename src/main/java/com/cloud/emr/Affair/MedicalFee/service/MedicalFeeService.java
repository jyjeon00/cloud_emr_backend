package com.cloud.emr.Affair.MedicalFee.service;

import com.cloud.emr.Affair.MedicalFee.dto.MedicalFeeRequest;
import com.cloud.emr.Affair.MedicalFee.dto.MedicalFeeResponse;
import com.cloud.emr.Affair.MedicalFee.entity.MedicalFeeEntity;
import com.cloud.emr.Affair.MedicalFee.entity.MedicalTypeEntity;
import com.cloud.emr.Affair.MedicalFee.repository.MedicalFeeRepository;
import com.cloud.emr.Affair.Treatment.entity.TreatmentEntity;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class MedicalFeeService {
    private final MedicalFeeRepository medicalFeeRepository;

    public MedicalFeeService(MedicalFeeRepository medicalFeeRepository) {
        this.medicalFeeRepository = medicalFeeRepository;
    }

    @Transactional
    public MedicalFeeResponse createMedicalFee(MedicalFeeRequest medicalFeeRequest, TreatmentEntity treatmentEntity, MedicalTypeEntity medicalTypeEntity) {
        MedicalFeeEntity medicalFeeEntity = MedicalFeeEntity.builder()
                .medicalTypeEntity(medicalTypeEntity)
                .treatmentEntity(treatmentEntity)
                .build();

        medicalFeeEntity = medicalFeeRepository.save(medicalFeeEntity);

        return new MedicalFeeResponse(
                medicalFeeEntity.getMedicalFeeId(),
                medicalFeeEntity.getMedicalTypeEntity().getMedicalTypeId(),
                medicalFeeEntity.getTreatmentEntity().getTreatmentId()
        );
    }


}
