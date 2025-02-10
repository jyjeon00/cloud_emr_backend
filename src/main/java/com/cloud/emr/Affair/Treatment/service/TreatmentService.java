package com.cloud.emr.Affair.Treatment.service;


import com.cloud.emr.Affair.CheckIn.entity.CheckInEntity;
import com.cloud.emr.Affair.Treatment.dto.TreatmentRequest;
import com.cloud.emr.Affair.Treatment.entity.TreatmentEntity;
import com.cloud.emr.Affair.Treatment.entity.TreatmentFeeEntity;
import com.cloud.emr.Affair.Treatment.repository.TreatmentFeeRepository;
import com.cloud.emr.Affair.Treatment.repository.TreatmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TreatmentService {

    private final TreatmentFeeRepository treatmentFeeRepository;
    private final TreatmentRepository treatmentRepository;

    public TreatmentService(TreatmentFeeRepository treatmentFeeRepository, TreatmentRepository treatmentRepository) {
        this.treatmentFeeRepository = treatmentFeeRepository;
        this.treatmentRepository = treatmentRepository;
    }

    public TreatmentFeeEntity findFeeById(Long treatmentFeeId) {
        return treatmentFeeRepository.findById(treatmentFeeId).orElse(null);
    }

    public TreatmentEntity createTreatment(TreatmentRequest treatmentRequest, CheckInEntity checkInEntity, TreatmentFeeEntity treatmentFeeEntity) {

        // TreatmentEntity 객체 생성
        TreatmentEntity treatmentEntity = treatmentRequest.toTreatmentEntity(checkInEntity, treatmentFeeEntity);

        // TreatmentEntity 저장
        return treatmentRepository.save(treatmentEntity);
    }
}