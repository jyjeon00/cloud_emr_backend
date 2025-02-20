package com.cloud.emr.Affair.Contract.service;


import com.cloud.emr.Affair.Contract.dto.ContractRelayRequest;
import com.cloud.emr.Affair.Contract.dto.ContractRelayResponse;
import com.cloud.emr.Affair.Contract.entity.ContractEntity;
import com.cloud.emr.Affair.Contract.entity.ContractRelayEntity;
import com.cloud.emr.Affair.Contract.repository.ContractRelayRepository;
import com.cloud.emr.Affair.Contract.repository.ContractRepository;
import com.cloud.emr.Affair.Patient.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContractRelayService {

    @Autowired
    private PatientService patientService;

    @Autowired
    private ContractService contractService;
    @Autowired
    private ContractRelayRepository contractRelayRepository;
    @Autowired
    private ContractRepository contractRepository;


    public ContractRelayResponse createContractRelay(ContractRelayRequest contractRelayRequest, Long patientNo) {
        ContractRelayEntity contractRelayEntity = ContractRelayEntity.builder()
                .patientEntity(patientService.findPatientByNo(patientNo))
                .contractEntity(contractService.findById(contractRelayRequest.getContractCode()))
                .build();

        contractRelayRepository.save(contractRelayEntity);

        return new ContractRelayResponse(
                contractRelayEntity.getContractRelayId(),
                contractRelayEntity.getPatientEntity().getPatientNo(),
                contractRelayEntity.getContractEntity().getContractCode()
        );
    }

    public ContractRelayResponse getContractRelayListByPatientNo(Long patientNo) {
        ContractRelayEntity contractRelayEntity = contractRelayRepository.findByPatientEntity_PatientNo(patientNo);

        if(contractRelayEntity == null) {
            return null;
        }

        return new ContractRelayResponse(
                contractRelayEntity.getContractRelayId(),
                contractRelayEntity.getPatientEntity().getPatientNo(),
                contractRelayEntity.getContractEntity().getContractCode()
        );
    }

    public ContractRelayResponse updateContractRelay(ContractRelayRequest contractRelayRequest, Long patientNo) {
        ContractRelayEntity contractRelayEntity = contractRelayRepository.findByPatientEntity_PatientNo(patientNo);

        ContractEntity contractEntity = contractRepository.findById(contractRelayRequest.getContractCode()).orElseThrow(
                () -> new IllegalArgumentException("해당 코드가 발견되지 않았습니다 : " + contractRelayRequest.getContractCode())
        );

        ContractRelayEntity updatedContractRelayEntity = ContractRelayEntity.builder()
                .contractRelayId(contractRelayEntity.getContractRelayId())
                .patientEntity(contractRelayEntity.getPatientEntity())
                .contractEntity(contractEntity)
                .build();

        contractRelayRepository.save(updatedContractRelayEntity);

        return new ContractRelayResponse(
                updatedContractRelayEntity.getContractRelayId(),
                updatedContractRelayEntity.getPatientEntity().getPatientNo(),
                updatedContractRelayEntity.getContractEntity().getContractCode()
        );
    }

    public ContractRelayResponse deleteContractRelay(Long patientNo) {
        ContractRelayResponse contractRelayResponse = getContractRelayListByPatientNo(patientNo);

        contractRelayRepository.deleteById(contractRelayResponse.getContractRelayId());

        return new ContractRelayResponse(
                contractRelayResponse.getContractRelayId(),
                contractRelayResponse.getPatientNo(),
                contractRelayResponse.getContractCode()
        );
    }
}
