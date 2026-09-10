package lk.ijse.mental_hospital.service;

import lk.ijse.mental_hospital.dto.UserDTO;

import java.util.List;

public interface UserService {

    void saveUser(UserDTO userDTO);

    List<UserDTO> getAllUsers();

    void updateUser(UserDTO userDTO);

    void changeUserStatus(long userId);

    List<UserDTO> filterUser(String userName);

}
