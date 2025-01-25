package com.cloud.emr.Affair.Reservation.dto;

import lombok.Getter;

import java.time.LocalDate;

// 단일 예약 조회
@Getter
public class ReservationResponse {

    private Long reservationId; // 예약 ID
    private String patientNo; // 환자 번호
    private LocalDate reservationDate; // 예약 날짜
    private boolean reservationYN; // 예약 여부 (상태)

}
