package com.cloud.emr.Affair.Qualification.controller;

import com.cloud.emr.Affair.Patient.service.PatientService;
import com.cloud.emr.Affair.Qualification.service.QualificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
@RequestMapping("/api/qualification")
public class QualificationController {

    private QualificationService qualificationService;
    private PatientService patientService;

    @Autowired
    public QualificationController(PatientService patientService, QualificationService qualificationService) {
        this.patientService = patientService;
        this.qualificationService = qualificationService;
    }

    //환자의 주민번호를 바탕으로 MockAPI에 보내는 과정
    @PostMapping("/insurance-info")
    public Mono<ResponseEntity<Map<String, Object>>> getPatientRrn(@RequestParam("patientNo") Long patientNo) {
        try {
            String targetPatientRrn = patientService.findPatientRrnByPatientNo(patientNo);
            return qualificationService.getInsuranceInfo(targetPatientRrn)
                    .map(insuranceInfo -> ResponseEntity.ok(Map.of(
                            "message", "조회 성공",
                            "patientNo", patientNo,
                            "insuranceInfo", insuranceInfo
                    )))
                    .defaultIfEmpty(ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                            Map.of(
                                    "error", "보험정보가 없습니다."
                            )
                    ));
        } catch (IllegalArgumentException e) {
            return Mono.just(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    Map.of(
                            "error", e.getMessage()
                    )
            ));
        }
    }
}
