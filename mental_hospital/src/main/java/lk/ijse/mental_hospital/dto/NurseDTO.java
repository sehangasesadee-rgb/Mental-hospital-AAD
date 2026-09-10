package lk.ijse.mental_hospital.dto;

import lk.ijse.mental_hospital.enumaration.NurseStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NurseDTO {

    private Long nurseId;
    private String nurseName;
    private String email;
    private String contactNumber;
    private Long departmentId;
    private Long wardId;
    private NurseStatus nurseStatus;
}