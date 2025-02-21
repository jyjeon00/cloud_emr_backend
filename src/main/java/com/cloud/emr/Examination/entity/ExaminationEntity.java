package com.cloud.emr.Examination.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import com.cloud.emr.Examination.Equipment.entity.EquipmentEntity;

@Entity(name = "Examination")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExaminationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "examination_id", nullable = false)
    private Long examinationId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "equipment_id", referencedColumnName = "equipment_id", nullable = false)
    private EquipmentEntity EquipmentEntity;

    @Column(name = "examination_name")
    private String examinationName;

    @Column(name = "examination_type")
    private String examinationType;

    @Column(name = "examination_equipment_name")
    private String examinationEquipmentName;

    // 주의 사항, 금지 사항
    @Column(name = "examination_constraints")
    private String examinationConstraints;

    @Column(name = "examination_location")
    private String examinationLocation;

    @Column(name = "examination_price")
    private String examinationPrice;

    // 이 아래는 그냥 다른 테이블에서 가져와서 화면에 띄울까?
}
