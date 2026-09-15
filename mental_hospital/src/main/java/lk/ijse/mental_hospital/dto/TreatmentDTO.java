package lk.ijse.mental_hospital.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class TreatmentDTO {
    private Long treatmentId;
    private String treatmentName;
    private String description;
    private LocalDate treatmentDate;
    private String treatmentStatus;
    private Long patientId;
    private Long doctorId;
}
