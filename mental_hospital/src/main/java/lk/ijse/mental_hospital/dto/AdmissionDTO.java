package lk.ijse.mental_hospital.dto;

import lk.ijse.mental_hospital.enumaration.AdmissionStatus;
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
    private AdmissionStatus admissionStatus;
    private Long patientId;
    private Long wardId;
    private Long bedId;
    private Long doctorId;
}