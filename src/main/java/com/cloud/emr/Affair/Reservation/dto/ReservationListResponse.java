package com.cloud.emr.Affair.Reservation.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
// 예약 목록 조회
public class ReservationListResponse {

    // 단일 예약을 목록으로 응답
    private List<ReservationResponse> reservationListResponse;

}
















