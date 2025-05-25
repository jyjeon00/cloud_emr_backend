package com.cloud.emr.Affair.Recess.service;

import com.cloud.emr.Affair.Recess.dto.RecessRequest;
import com.cloud.emr.Affair.Recess.dto.RecessResponse;
import com.cloud.emr.Affair.Recess.entity.RecessEntity;
import com.cloud.emr.Affair.Recess.repository.RecessRepository;
import com.cloud.emr.Main.User.entity.UserEntity;
import com.cloud.emr.Main.User.service.UserService;
import com.cloud.emr.Main.User.type.RoleType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecessService {

    private final RecessRepository recessRepository;
    private final UserService userService;

    /** 1. 휴진 등록 **/
    @Transactional
    public RecessResponse registerRecess(UserEntity user, RecessRequest recessRequest) {
        validateTimeSlot(recessRequest.getRecessStart(), recessRequest.getRecessEnd());

        if (user.getRole() != recessRequest.getRole()) {
            throw new RuntimeException("사용자의 역할이 일치하지 않습니다.");
        }

        LocalDateTime start = adjustToHalfHour(recessRequest.getRecessStart());
        LocalDateTime end = adjustToHalfHour(recessRequest.getRecessEnd());

        RecessEntity recessEntity = RecessEntity.builder()
                .userEntity(user)
                .recessStart(start)
                .recessEnd(end)
                .recessReason(recessRequest.getRecessReason())
                .build();

        recessRepository.save(recessEntity);
        return RecessResponse.from(recessEntity);
    }



    /** 2. 휴진 수정 **/
    @Transactional
    public RecessResponse updateRecess(UserEntity user, Long recessId, RecessRequest recessRequest) {
        validateTimeSlot(recessRequest.getRecessStart(), recessRequest.getRecessEnd());

        RecessEntity recessEntity = recessRepository.findById(recessId)
                .orElseThrow(() -> new RuntimeException("휴진 정보를 찾을 수 없습니다."));

        if (!recessEntity.getUserEntity().getId().equals(user.getId())) {
            throw new RuntimeException("본인의 휴진 정보만 수정할 수 있습니다.");
        }

        if (user.getRole() != recessRequest.getRole()) {
            throw new RuntimeException("사용자의 역할이 일치하지 않습니다.");
        }

        LocalDateTime start = adjustToHalfHour(recessRequest.getRecessStart());
        LocalDateTime end = adjustToHalfHour(recessRequest.getRecessEnd());

        RecessEntity updated = RecessEntity.builder()
                .id(recessEntity.getId())
                .userEntity(user)
                .recessStart(start)
                .recessEnd(end)
                .recessReason(recessRequest.getRecessReason())
                .build();

        recessRepository.save(updated);
        return RecessResponse.from(updated);
    }



    /** 3. 휴진 삭제 **/
    @Transactional
    public void deleteRecess(UserEntity user, Long recessId) {
        RecessEntity recessEntity = recessRepository.findById(recessId)
                .orElseThrow(() -> new RuntimeException("휴진 정보를 찾을 수 없습니다."));

        if (!recessEntity.getUserEntity().getId().equals(user.getId())) {
            throw new RuntimeException("본인의 휴진 정보만 삭제할 수 있습니다.");
        }

        recessRepository.delete(recessEntity);
    }



    /** 4. 휴진 목록 조회 **/
    @Transactional(readOnly = true)
    public List<RecessResponse> listByRole(UserEntity user, RoleType role) {
        if (user.getRole() != role) {
            throw new RuntimeException("조회 권한이 없습니다.");
        }

        return recessRepository.findByUserEntity_Role(role).stream()
                .map(RecessResponse::from)
                .collect(Collectors.toList());
    }

    private void validateTimeSlot(LocalDateTime startTime, LocalDateTime endTime) {
        if (startTime.getMinute() % 30 != 0 || endTime.getMinute() % 30 != 0) {
            throw new IllegalArgumentException("시간은 반드시 30분 단위여야 합니다.");
        }
        if (endTime.isBefore(startTime) || endTime.equals(startTime)) {
            throw new IllegalArgumentException("종료 시간은 시작 시간 이후여야 합니다.");
        }
    }

    private LocalDateTime adjustToHalfHour(LocalDateTime time) {
        int minute = time.getMinute();
        int adjustedMinute = (minute < 30) ? 0 : 30;
        return time.withMinute(adjustedMinute).withSecond(0).withNano(0);
    }

}
