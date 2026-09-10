package lk.ijse.mental_hospital.dto;


import lk.ijse.mental_hospital.enumaration.BedStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BedDTO {
    private Long bedId;
    private String bedNumber;
    private String bedType;
    private BedStatus bedStatus;
    private Long wardId;
}
