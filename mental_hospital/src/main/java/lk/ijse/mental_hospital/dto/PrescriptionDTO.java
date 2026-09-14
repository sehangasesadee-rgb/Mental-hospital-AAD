package lk.ijse.mental_hospital.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrescriptionDTO {
    private Long prescriptionId;
    private LocalDate prescriptionDate;
    private String dosage;
    private String frequency;
    private Integer durationDays;
    private String instructions;
    private Long patientId;
    private Long doctorId;
    private Long medicationId;
}
