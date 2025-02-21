package com.cloud.emr.Affair.Contract.controller;

import com.cloud.emr.Affair.Contract.dto.ContractRelayRequest;
import com.cloud.emr.Affair.Contract.dto.ContractRelayResponse;
import com.cloud.emr.Affair.Contract.entity.ContractRelayEntity;
import com.cloud.emr.Affair.Contract.service.ContractRelayService;
import com.cloud.emr.Affair.Patient.entity.PatientEntity;
import com.cloud.emr.Affair.Patient.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/contractrelay")
public class ContractRelayController {


    @Autowired
    private PatientService patientService;
    @Autowired
    private ContractRelayService contractRelayService;


    //환자 ID에 따른 계약처 조회 (계약처 관리도 조회 될 수 있도록)
    @GetMapping("/search")
    public ResponseEntity<Object> getContractRelayList(@RequestParam Long patientNo){
        PatientEntity patientEntity = patientService.findPatientByNo(patientNo);

        if(patientEntity == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of(
                    "message", "해당 환자를 찾을 수 없습니다.",
                    "data", patientNo
            ));
        }

        ContractRelayResponse response = contractRelayService.getContractRelayListByPatientNo(patientNo);

        return ResponseEntity.status(HttpStatus.OK).body(Map.of(
                "message","조회 성공",
                "data", response
        ));
    }

    //환자 ID에 따른 계약처 생성
    @PostMapping("/create")
    public ResponseEntity<Object> createContractRelay(@RequestBody ContractRelayRequest contractRelayRequest, @RequestParam Long patientNo) {
        try {
            PatientEntity patient = patientService.findPatientByNo(patientNo);

            if (patient == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of(
                        "message", "Patient not found!",
                        "patientNo", patientNo
                ));
            }

            ContractRelayResponse response = contractRelayService.createContractRelay(contractRelayRequest, patientNo);

            return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(
                    "message","환자 계약처 생성",
                    "data", response
            ));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
                    "message","생성 작업 중 오류 발생",
                    "error", e.getMessage()
            ));
        }

    }

    //환자 ID에 따른 계약처 코드 수정
    @PostMapping("/update")
    public ResponseEntity<Object> updateContractRelay(@RequestBody ContractRelayRequest contractRelayRequest, @RequestParam Long patientNo) {
        try {
            PatientEntity patient = patientService.findPatientByNo(patientNo);
            if (patient == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of(
                        "message","해당 환자를 찾을 수 없습니다.",
                        "data", patientNo
                ));
            }

            ContractRelayResponse isExistResponse = contractRelayService.getContractRelayListByPatientNo(patientNo);
            if(isExistResponse == null){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of(
                        "message", "해당 환자는 계약 관계가 없습니다.",
                        "data", patientNo
                ));
            }

            ContractRelayResponse response = contractRelayService.updateContractRelay(contractRelayRequest, patientNo);

            return ResponseEntity.status(HttpStatus.OK).body(Map.of(
                    "message","계약 관계 수정 성공",
                    "data", response
            ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
                    "message","내부 장애가 발생하였습니다.",
                    "error", e.getMessage()
            ));
        }
    }

    //환자 ID에 따른 계약처 삭제
    @PostMapping("/delete")
    public ResponseEntity<Object> deleteContractRelay(@RequestParam Long patientNo) {
        try {
            PatientEntity patient = patientService.findPatientByNo(patientNo);
            if (patient == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of(
                        "message","해당 환자를 찾을 수 없습니다.",
                        "data", patientNo
                ));
            }

            ContractRelayResponse response = contractRelayService.getContractRelayListByPatientNo(patientNo);
            if(response == null){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of(
                        "message","해당 환자는 계약 관계가 없습니다."
                ));
            }

            ContractRelayResponse deleteResponse = contractRelayService.deleteContractRelay(patientNo);

            return ResponseEntity.status(HttpStatus.OK).body(Map.of(
                    "message","삭제 성공",
                    "data", deleteResponse
            ));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
                    "message","내부 장애가 발생하였습니다.",
                    "error", e.getMessage()
            ));
        }
    }

}