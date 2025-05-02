package com.cloud.emr.Affair.Contract.entity;


import com.cloud.emr.Affair.Patient.entity.PatientEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "Contract_relay")
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ContractRelayEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contract_relay_id", nullable = false, columnDefinition = "INT")
    private Long contractRelayId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_no", referencedColumnName = "patient_no", nullable = false)
    private PatientEntity patientEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contract_code", referencedColumnName = "contract_code", nullable = false)
    private ContractEntity contractEntity;

}
