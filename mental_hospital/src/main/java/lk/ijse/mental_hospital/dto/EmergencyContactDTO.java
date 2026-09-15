package lk.ijse.mental_hospital.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmergencyContactDTO {

    private Long emergencyContactId;
    private String contactName;
    private String relationship;
    private String contactNumber;
    private String address;
    private Long patientId;
}