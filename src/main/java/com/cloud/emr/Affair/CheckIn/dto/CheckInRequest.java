package com.cloud.emr.Affair.CheckIn.dto;


import com.cloud.emr.Affair.CheckIn.entity.CheckInEntity;
import com.cloud.emr.Main.User.entity.UserEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class CheckInRequest {

    @NotBlank(message = "환자번호는 필수 값입니다.")
    @Size(min = 8, max = 8, message = "환자번호는 반드시 8자리여야 합니다.")
    private String patientNo;

    @NotNull(message = "접수 목적은 필수 값입니다.")
    @Size(max = 500, message = "접수 목적은 최대 500자까지 가능합니다.")
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
