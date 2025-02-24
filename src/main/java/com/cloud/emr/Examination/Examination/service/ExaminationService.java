package com.cloud.emr.Examination.Examination.service;

import com.cloud.emr.Affair.Patient.entity.PatientEntity;
import com.cloud.emr.Examination.Equipment.entity.EquipmentEntity;
import com.cloud.emr.Examination.Equipment.repository.EquipmentRepository;
import com.cloud.emr.Examination.Examination.dto.ExaminationRegisterRequest;
import com.cloud.emr.Examination.Examination.dto.ExaminationResponse;
import com.cloud.emr.Examination.Examination.dto.ExaminationUpdateRequest;
import com.cloud.emr.Examination.Examination.entity.ExaminationEntity;
import com.cloud.emr.Examination.Examination.repository.ExaminationRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ExaminationService {


    @Autowired
    private ExaminationRepository examinationRepository;

    @Autowired
    private EquipmentRepository equipmentRepository;

//    @Autowired
//    private EquipmentService equipmentService;

    public ExaminationEntity registerExamination(ExaminationRegisterRequest examinationRegisterRequest) {

        EquipmentEntity foundEquipment = null;

        if (examinationRegisterRequest.getEquipmentId() != null) {
            foundEquipment = equipmentRepository.findByEquipmentId(examinationRegisterRequest.getEquipmentId());
        }

        ExaminationEntity examinationEntity = ExaminationEntity.builder()
                .equipmentEntity(foundEquipment)
                .examinationName(examinationRegisterRequest.getExaminationName())
                .examinationType(examinationRegisterRequest.getExaminationType())
                .examinationConstraints(examinationRegisterRequest.getExaminationConstraints())
                .examinationLocation(examinationRegisterRequest.getExaminationLocation())
                .examinationPrice(examinationRegisterRequest.getExaminationPrice())
                .build();

        return examinationRepository.save(examinationEntity);
    }

    public ExaminationResponse readExamination(Long examinationId) {

        Optional<ExaminationEntity> examinationEntityOptional = examinationRepository.findByExaminationId(examinationId);

        if (examinationEntityOptional.isPresent()) {
            ExaminationEntity examinationEntity = examinationEntityOptional.get();

            return new ExaminationResponse(
                    examinationEntity.getExaminationId(),
                    examinationEntity.getEquipmentEntity().getEquipmentId(),
                    examinationEntity.getEquipmentEntity().getEquipmentName(),
                    examinationEntity.getExaminationName(),
                    examinationEntity.getExaminationType(),
                    examinationEntity.getExaminationConstraints(),
                    examinationEntity.getExaminationLocation(),
                    examinationEntity.getExaminationPrice()
            );
        }
        return null;
    }

    public List<ExaminationResponse> readExaminationByEuipmentId(Long equipmentID) {

        EquipmentEntity foundEquipment = equipmentRepository.findByEquipmentId(equipmentID);
        if (foundEquipment == null) {
            throw new IllegalStateException("장비를 찾을 수 없습니다.");
        }

        List<ExaminationEntity> examinationEntities = examinationRepository.findAllByEquipmentEntity(foundEquipment);

        return examinationEntities.stream()
                .map(examinationEntity -> new ExaminationResponse(
                        examinationEntity.getExaminationId(),
                        examinationEntity.getEquipmentEntity().getEquipmentId(),
                        examinationEntity.getEquipmentEntity().getEquipmentName(),
                        examinationEntity.getExaminationName(),
                        examinationEntity.getExaminationType(),
                        examinationEntity.getExaminationConstraints(),
                        examinationEntity.getExaminationLocation(),
                        examinationEntity.getExaminationPrice()
                ))
                .collect(Collectors.toList());
    }

    public List<ExaminationResponse> readAllExamination() {

        List<ExaminationEntity> examinationEntities = examinationRepository.findAll();

        return examinationEntities.stream()
                .map(examinationEntity -> new ExaminationResponse(
                        examinationEntity.getExaminationId(),
                        examinationEntity.getEquipmentEntity().getEquipmentId(),
                        examinationEntity.getEquipmentEntity().getEquipmentName(),
                        examinationEntity.getExaminationName(),
                        examinationEntity.getExaminationType(),
                        examinationEntity.getExaminationConstraints(),
                        examinationEntity.getExaminationLocation(),
                        examinationEntity.getExaminationPrice()
                ))
                .collect(Collectors.toList());
    }

    @Transactional
    public ExaminationEntity updateExamination(Long examinationId, ExaminationUpdateRequest examinationUpdateRequest) {

        Optional<ExaminationEntity> examinationEntityOptional = examinationRepository.findByExaminationId(examinationId);

        if (examinationEntityOptional.isPresent()) {
            EquipmentEntity foundEquipment = null;

            Long equipmentId = examinationEntityOptional.get().getEquipmentEntity().getEquipmentId();

            if (equipmentId != null) {
                foundEquipment = equipmentRepository.findByEquipmentId(equipmentId);
            }

            try {
                if (examinationUpdateRequest.getEquipmentId() != null) {
                    foundEquipment = equipmentRepository.findByEquipmentId(examinationUpdateRequest.getEquipmentId());
                }
            } catch (Exception e) {
                System.out.println("Examination Update : " + e);
                throw new RuntimeException("해당 장비 정보가 없습니다.");
            }

            ExaminationEntity updatedEntity = ExaminationEntity.builder()
                    .examinationId(examinationId)
                    .equipmentEntity(foundEquipment)
                    .examinationName(examinationUpdateRequest.getExaminationName() != null ? examinationUpdateRequest.getExaminationName() : examinationEntityOptional.get().getExaminationName())
                    .examinationType(examinationUpdateRequest.getExaminationType() != null ? examinationUpdateRequest.getExaminationType() : examinationEntityOptional.get().getExaminationType())
                    .examinationConstraints(examinationUpdateRequest.getExaminationConstraints() != null ? examinationUpdateRequest.getExaminationConstraints() : examinationEntityOptional.get().getExaminationConstraints())
                    .examinationLocation(examinationUpdateRequest.getExaminationLocation() != null ? examinationUpdateRequest.getExaminationLocation() : examinationEntityOptional.get().getExaminationLocation())
                    .examinationPrice(examinationUpdateRequest.getExaminationPrice() != null ? examinationUpdateRequest.getExaminationPrice() : examinationEntityOptional.get().getExaminationPrice())
                    .build();

            return examinationRepository.save(updatedEntity);
        }
        return null;
    }

    @Transactional
    public ExaminationResponse deleteExamination(Long examinationID) {

        ExaminationEntity examinationEntity = examinationRepository.findByExaminationId(examinationID)
                          .orElseThrow(() -> new RuntimeException("해당 검사 정보가 없습니다."));;

        ExaminationResponse deletedExamination = new ExaminationResponse(
                examinationEntity.getExaminationId(),
                examinationEntity.getEquipmentEntity().getEquipmentId(),
                examinationEntity.getEquipmentEntity().getEquipmentName(),
                examinationEntity.getExaminationName(),
                examinationEntity.getExaminationType(),
                examinationEntity.getExaminationConstraints(),
                examinationEntity.getExaminationLocation(),
                examinationEntity.getExaminationPrice()
        );

        examinationRepository.delete(examinationEntity);

        return deletedExamination;
    }
}
