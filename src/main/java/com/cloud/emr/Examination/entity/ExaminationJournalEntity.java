package com.cloud.emr.Examination.entity;

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
public class ExaminationJournalEntity {

    @Id

    @Column(name = "examination_no")
    private String examinationNo;

    @Column(name = "patient_no")
    private String patientNo;

    @Column(name = "treatment_no")
    private String treatmentNo;

    @Column(name = "user_no")
    private String userNo;

    @Column(name = "doctor_no")
    private String doctorNo;

    @Column(name = "equipment_no")
    private String equipmentNo;

    @Column(name = "examination_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date examinationTime;

    @Column(name = "examination_equipment_usage")
    private String examinationEquipmentUsage;

    @Column(name = "examination_notes")
    private String examinationNotes;

    // 이 아래는 그냥 다른 테이블에서 가져와서 화면에 띄울까?
//    @Column(name = "examination_name")
//    private String examinationName;
//
//    @Column(name = "examination_type")
//    private String examinationType;
//
//    @Column(name = "patient_name")
//    private String patientName;
//
//    @Column(name = "User_name")
//    private String UserName;
//
//    @Column(name = "Doctor_name")
//    private String DoctorName;
//
//    @Column(name = "examination_equipment")
//    private String examinationEquipment;
}
