package com.cloud.emr.Affair.CheckIn.dto;


import com.cloud.emr.Affair.CheckIn.entity.CheckInEntity;
import com.cloud.emr.Main.User.entity.UserEntity;
import lombok.Getter;

@Getter
public class CheckInRequest {

    private String patientNo;
    private Long userId;
    private String checkInPurpose;

    // CheckInEntity로 변환
    public CheckInEntity toCheckInEntity(UserEntity userEntity) {
        return CheckInEntity.builder()
                .patientNo(this.patientNo)
                .userEntity(userEntity)
                .checkInPurpose(this.checkInPurpose)
                .build();
    }
}
