package com.cloud.emr.Affair.Reservation.controller;

import com.cloud.emr.Affair.CheckIn.repository.CheckInRepository;
import com.cloud.emr.Affair.Patient.repository.PatientRepository;
import com.cloud.emr.Affair.Reservation.entity.ReservationEntity;
import com.cloud.emr.Affair.Reservation.repository.ReservationRepository;
import com.cloud.emr.Affair.Reservation.service.ReservationService;
import com.cloud.emr.Main.User.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservation")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private CheckInRepository checkInRepository;

    // 1. 환자 예약 (예약은 환자 접수가 되면 사라짐)


    // 2. 예약 목록 조회


    // 3. 예약 취소


    // 4. 예약 변경




}
