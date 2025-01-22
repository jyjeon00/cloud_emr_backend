package com.cloud.emr.Affair.CheckIn.controller;

import com.cloud.emr.Affair.CheckIn.dto.CheckInCancelRequest;
import com.cloud.emr.Affair.CheckIn.dto.CheckInListResponse;
import com.cloud.emr.Affair.CheckIn.dto.CheckInRequest;
import com.cloud.emr.Affair.CheckIn.entity.CheckInEntity;
import com.cloud.emr.Affair.CheckIn.repository.CheckInRepository;
import com.cloud.emr.Affair.CheckIn.service.CheckInService;
import com.cloud.emr.Main.User.entity.UserEntity;
import com.cloud.emr.Main.User.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/checkin")
public class CheckInController {

    @Autowired
    private CheckInService checkInService;

    @Autowired
    private CheckInRepository checkInRepository;

    @Autowired
    private UserRepository userRepository;

    // 사용자 조회 메서드
    private UserEntity findUserById(Long userId) {
        Optional<UserEntity> userEntity = userRepository.findById(userId);  // findById 메서드 사용
        return userEntity.orElse(null); // 유저가 없으면 null 반환
    }

    // 1. 접수 등록
    @PostMapping("/register")
    public ResponseEntity<Object> registerCheckIn(@RequestBody CheckInRequest checkInRequest, @RequestParam Long userId) {
        try {

            // 사용자 조회
            UserEntity userEntity = findUserById(userId);// findUserById 메서드를 사용하여 유저 정보를 가져옵니다.
            if (userEntity == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", "User not found"));
            }

            // 환자 번호 유효성 검사 (이미 존재하는 환자 번호는 중복을 피해야 함)
            CheckInEntity existingCheckIn = checkInRepository.findByPatientNo(checkInRequest.getPatientNo());
            if (existingCheckIn != null) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("message", "Patient already checked in"));
            }

            // 접수 등록 처리
            CheckInEntity newCheckIn = checkInService.createCheckIn(checkInRequest, userEntity);

            // 성공적인 접수 처리
            return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("message", "접수 성공", "data", newCheckIn));

        } catch (Exception e) {
            // 예외 처리
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of(
                    "message", "접수 실패",
                    "error", e.getMessage()
            ));
        }
    }


    // 2. 접수 목록 조회
    @GetMapping("/list")
    public ResponseEntity<Object> getCheckInList() {
        List<CheckInListResponse> checkInList = checkInService.getCheckInList();

        if (checkInList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("목록에 접수가 없습니다.");
        }

        return ResponseEntity.ok(checkInList); // 접수 목록 반환
    }


    // 3. 접수 취소
    @PostMapping("/cancel")
    public ResponseEntity<Object> cancelCheckIn(@RequestParam Long checkInId) {
        try {
            checkInService.cancelCheckIn(checkInId);  // checkInId를 전달받아 취소 처리
            return ResponseEntity.ok("접수 취소 완료");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("존재하지 않는 접수");
        }
    }





}


/*
------------------------------------------------------------------------------

 회원가입 이후

 1. 접수 요청

 POST

 http://localhost:8081/api/checkin/register?userId=

 raw

 {
    "patientNo": "12345678",
    "checkInPurpose": "정기검진"
 }

------------------------------------------------------------------------------

 2. 접수 목록 (하나라도 접수된게 있으면 조회 가능)

 GET

 http://localhost:8081/api/checkin/list

 none


------------------------------------------------------------------------------

 3. 접수 취소

  POST

  http://localhost:8081/api/checkin/cancel?checkInId=

  none

------------------------------------------------------------------------------

 */

