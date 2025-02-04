package com.cloud.emr.Affair.Reservation.entity;

import com.cloud.emr.Affair.CheckIn.entity.CheckInEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity(name = "Reservation")
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReservationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="reservation_id", nullable = false, columnDefinition = "INT")
    private Long reservationId; // 예약 ID

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "checkIn_Id", referencedColumnName = "checkIn_Id", nullable = false, columnDefinition = "INT")
    private CheckInEntity checkInId; // 접수 ID

    @Column(name = "patient_no", nullable = false, length = 8)
    private String patientNo; // 환자 번호

    @Column(name = "reservation_date")
    @Temporal(TemporalType.DATE)
    private LocalDate reservationDate; // 예약 날짜

    @Column(name = "reservation_YN", nullable = false, length = 1)
    private String reservationYn; // 예약 여부

    @Column(name = "reservation_change_date")
    @Temporal(TemporalType.DATE)
    private LocalDate reservationChangeDate; // 예약 변경 날짜

    @Column(name = "reservation_change_cause", length = 100)
    private String reservationChangeCause; // 예약 변경 사유

}

// length = 20이 varchar(20)이라는 뜻
