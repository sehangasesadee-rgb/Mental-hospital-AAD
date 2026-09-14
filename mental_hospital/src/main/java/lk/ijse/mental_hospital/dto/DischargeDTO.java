package lk.ijse.mental_hospital.dto;

import lk.ijse.mental_hospital.enumaration.DischargeStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DischargeDTO {

    private Long dischargeId;
    private LocalDate dischargeDate;
    private String remarks;
    private DischargeStatus dischargeStatus;
    private Long admissionId;
    private Long doctorId;
}
