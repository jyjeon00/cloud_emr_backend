package com.cloud.emr.Affair.Contract.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ContractRelayResponse {
    private Long contractRelayId;
    private Long patientNo;
    private Long contractCode;
}
