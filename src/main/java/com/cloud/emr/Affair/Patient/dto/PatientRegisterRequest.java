package com.cloud.emr.Affair.Patient.dto;


import com.cloud.emr.Affair.Patient.entity.PatientEntity;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Builder
public class PatientRegisterRequest {
    private final String patientNo;
    private final String patientName;
    private final String patientGender;
    private final Date patientBirth;
    private final String patientAddress;
    private final String patientEmail;
    private final String patientTel;
    private final String patientForeign;
    private final String patientPassport;
    private final String patientHypassYN;
    private final LocalDate patientLastVisit;
    private final String guardian;

}
