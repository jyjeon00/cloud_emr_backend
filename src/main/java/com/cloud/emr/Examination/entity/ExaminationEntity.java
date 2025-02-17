package com.cloud.emr.Examination.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "Examination")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExaminationEntity {

    @Id

    @Column(name = "examination_no")
    private String examinationNo;

    @Column(name = "equipment_no")
    private String equipmentNo;

    @Column(name = "examination_name")
    private String examinationName;

    @Column(name = "examination_type")
    private String examinationType;

    @Column(name = "examination_equipment")
    private String examinationEquipment;

    @Column(name = "examination_constraints")
    private String examinationConstraints;

    @Column(name = "examination_location")
    private String examinationLocation;

    @Column(name = "examination_price")
    private String examinationPrice;

    // 이 아래는 그냥 다른 테이블에서 가져와서 화면에 띄울까?
}
