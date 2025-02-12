package com.cloud.emr.Affair.MedicalFee.service;

import com.cloud.emr.Affair.MedicalFee.dto.MedicalFeeRequest;
import com.cloud.emr.Affair.MedicalFee.dto.MedicalFeeResponse;
import com.cloud.emr.Affair.MedicalFee.entity.MedicalFeeEntity;
import com.cloud.emr.Affair.MedicalFee.entity.MedicalTypeEntity;
import com.cloud.emr.Affair.MedicalFee.repository.MedicalFeeRepository;
import com.cloud.emr.Affair.Treatment.dto.TreatmentResponse;
import com.cloud.emr.Affair.Treatment.entity.TreatmentEntity;
import com.cloud.emr.Affair.Treatment.repository.TreatmentRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicalFeeService {
    private final MedicalFeeRepository medicalFeeRepository;
    private final TreatmentRepository treatmentRepository;

    public MedicalFeeService(MedicalFeeRepository medicalFeeRepository, TreatmentRepository treatmentRepository) {
        this.medicalFeeRepository = medicalFeeRepository;
        this.treatmentRepository = treatmentRepository;
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

    // medicalfee 생성시 자동으로 총 금액이 업데이트 되도록
    // 추후 MedicalType Update시 수정된 총 금액이 반영되도록 업데이트가 필요함
    @Transactional
    public TreatmentResponse updateTotalFeeForTreatment(Long treatmentId) {
        TreatmentEntity treatment = treatmentRepository.findById(treatmentId).orElseThrow(
                () -> new IllegalArgumentException("Invalid treatment Id: " + treatmentId)
        );


        List<MedicalFeeEntity> medicalFees = medicalFeeRepository.findMedicalFeeEntitiesByTreatmentEntity(treatment);

        Long totalFee = medicalFees.stream()
                .mapToLong(medicalFee -> medicalFee.getMedicalTypeEntity().getMedicalTypeFee())
                .sum();

        TreatmentEntity setTreatment = treatmentRepository.findById(treatmentId)
                .orElseThrow(() -> new IllegalArgumentException("해당 진료 없음 : " + treatmentId));

        setTreatment.setTreatmentTotalFee(totalFee);
        treatmentRepository.save(setTreatment);

        TreatmentResponse treatmentResponse = new TreatmentResponse(
                setTreatment.getTreatmentId(),
                setTreatment.getCheckInEntity().getCheckInId(),
                setTreatment.getTreatmentDate(),
                setTreatment.getTreatmentStatus(),
                setTreatment.getTreatmentNextDate(),
                setTreatment.getTreatmentComment(),
                setTreatment.getTreatmentDept(),
                setTreatment.getTreatmentDoc(),
                setTreatment.getTreatmentTotalFee()
        );
        return treatmentResponse;



    }


}
