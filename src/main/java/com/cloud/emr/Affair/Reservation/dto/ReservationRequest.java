package com.cloud.emr.Affair.Reservation.dto;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class ReservationRequest {

    private Long reservationId; // 예약 ID
    private String patientNo; // 환자 번호
    private LocalDate reservationDate; // 예약 날짜



}
