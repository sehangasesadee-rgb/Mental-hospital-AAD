package lk.ijse.mental_hospital.dto;

import lk.ijse.mental_hospital.enumaration.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SUserDTO {

    private Long userId;
    private String userName;
    private String userPassword;
    private UserStatus userStatus;
    private Long roleId;
    private String token;

}
