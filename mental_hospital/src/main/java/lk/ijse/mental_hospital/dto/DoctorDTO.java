package lk.ijse.mental_hospital.dto;

import lk.ijse.mental_hospital.enumaration.DoctorSpecialization;
import lk.ijse.mental_hospital.enumaration.DoctorStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DoctorDTO {

    private Long doctorId;
    private String doctorName;
    private String email;
    private String contactNumber;
    private DoctorSpecialization specialization;
    private Long departmentId;
    private DoctorStatus doctorStatus;
}