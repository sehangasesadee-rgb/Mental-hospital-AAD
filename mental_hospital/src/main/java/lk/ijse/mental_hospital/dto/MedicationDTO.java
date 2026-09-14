package lk.ijse.mental_hospital.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedicationDTO {
    private Long medicationId;
    private String medicationName;
    private String dosage;
    private String description;
    private Integer quantity;
    private String medicationStatus;

}
