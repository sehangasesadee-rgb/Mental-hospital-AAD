package lk.ijse.mental_hospital.dto;

import lk.ijse.mental_hospital.enumaration.WardStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WardDTO {

    private Long wardId;
    private String wardName;
    private String wardType;
    private Integer capacity;
    private Long departmentId;
    private WardStatus wardStatus;
}