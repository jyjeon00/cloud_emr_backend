package com.cloud.emr.Affair.Treatment.repository;

import com.cloud.emr.Affair.Treatment.entity.TreatmentFeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TreatmentFeeRepository extends JpaRepository<TreatmentFeeEntity, Long> {

    default TreatmentFeeEntity getOrCreateDefault() {
        TreatmentFeeEntity defaultEntity = new TreatmentFeeEntity();
        defaultEntity.setTreatmentType("");
        defaultEntity.setTreatmentTypeFee(0L);
        return save(defaultEntity); // 기본값을 가진 엔터티를 DB에 저장 후 반환
    }

}
