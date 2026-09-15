package lk.ijse.mental_hospital.service;

import lk.ijse.mental_hospital.dto.LoginDTO;
import lk.ijse.mental_hospital.dto.UserDTO;

public interface AuthenticationService {
    String login(LoginDTO loginDTO);

    UserDTO getUserDTO(String name);
}
