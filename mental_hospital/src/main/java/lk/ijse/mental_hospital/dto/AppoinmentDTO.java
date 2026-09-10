package lk.ijse.mental_hospital.dto;


import lk.ijse.mental_hospital.enumaration.AppoinmentStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AppoinmentDTO {
    private Long appointmentId;
    private LocalDate appointmentDate;
    private LocalTime appointmentTime;
    private AppoinmentStatus appointmentStatus;
    private String reason;
    private Long patientId;
    private Long doctorId;
}
