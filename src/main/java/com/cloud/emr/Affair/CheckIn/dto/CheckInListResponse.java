package com.cloud.emr.Affair.CheckIn.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CheckInListResponse {

    private Long checkInId;    // 접수 ID
    private String patientNo;  // 환자 번호
    private String checkInPurpose;  // 접수 목적
    private String userName;   // 접수한 유저 이름

}
