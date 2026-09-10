package lk.ijse.mental_hospital.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdmissionDTO {

    private Long admissionId;
    private LocalDate admissionDate;
    private String reason;
    private String admissionStatus;
    private Long patientId;
    private Long wardId;
    private Long bedId;
    private Long doctorId;
}