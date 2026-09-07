package lk.ijse.mental_hospital.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PatientDTO {

    private Long patientId;

    private String patientName;

    private LocalDate dateOfBirth;

    private String gender;

    private String contactNumber;

    private String address;

    private String email;

    private String patientStatus;
}