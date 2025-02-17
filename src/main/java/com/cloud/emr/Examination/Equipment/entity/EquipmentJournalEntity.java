package com.cloud.emr.Examination.Equipment.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity(name = "ExaminationJournal")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EquipmentJournalEntity {

    @Id

    @Column(name = "equipment_no")
    private String equipmentNo;

    @Column(name = "user_no")
    private String userNo;

    @Column(name = "equipment_inspection_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date equipmentInspectionDate;

    @Column(name = "equipment_result")
    private String equipmentResult;

    @Column(name = "equipment_record")
    private String equipmentRecord;

    @Column(name = "equipment_notes")
    private String equipmentNotes;

    // 이 아래는 그냥 다른 테이블에서 가져와서 화면에 띄울까?
//    @Column(name = "equipment_location")
//    private String equipmentLocation;

    //@Column(name = "equipment_name")
    //private String equipmentName;
}
