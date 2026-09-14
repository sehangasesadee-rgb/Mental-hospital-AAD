package lk.ijse.mental_hospital.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DiagnosisDTO {

    private Long diagnosisId;
    private String diagnosisName;
    private String description;
    private LocalDate diagnosisDate;
    private Long patientId;
    private Long doctorId;
}
