package com.cloud.emr.Examination.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import java.util.Date;

@Entity(name = "ExaminationResult")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExaminationResultEntity {

    @Id

    @Column(name = "examination_no")
    private String examinationNo;

    @Column(name = "patient_no")
    private String patientNo;

    @Column(name = "treatment_no")
    private String treatmentNo;

    @Column(name = "examination_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date examinationDate;

    @Column(name = "examination_result")
    private String examinationResult;

    @Column(name = "examination_normal")
    private String examinationNormal;

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
}
