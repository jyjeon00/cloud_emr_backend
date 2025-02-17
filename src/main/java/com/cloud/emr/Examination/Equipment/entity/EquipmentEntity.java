package com.cloud.emr.Examination.Equipment.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "Examination")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EquipmentEntity {

    @Id

    @Column(name = "equipment_no")
    private String equipmentNo;

    @Column(name = "equipment_name")
    private String equipmentName;

    @Column(name = "equipment_manufacturer")
    private String equipmentManufacturer;

    @Column(name = "equipment_location")
    private String equipmentLocation;

    @Column(name = "equipment_state")
    private String equipmentState;

    @Column(name = "equipment_schedule")
    private String equipmentSchedule;

    // 이 아래는 그냥 다른 테이블에서 가져와서 화면에 띄울까?
}
