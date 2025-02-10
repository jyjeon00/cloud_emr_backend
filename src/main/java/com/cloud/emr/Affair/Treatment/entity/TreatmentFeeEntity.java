package com.cloud.emr.Affair.Treatment.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "Treatment_Fee")
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TreatmentFeeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "treatment_fee_id", nullable = false, columnDefinition = "INT")
    private Long treatmentFeeId;

    @Column(name = "treatment_type", nullable = false, columnDefinition = "NVARCHAR(50)")
    private String treatmentType;

    @Column(name = "treatment_type_fee", nullable = false)
    private Long treatmentTypeFee;
}
