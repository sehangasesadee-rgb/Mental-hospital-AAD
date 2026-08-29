package lk.ijse.mental_hospital.service;

import lk.ijse.mental_hospital.dto.RoleDTO;

import java.util.List;

public interface RoleService {
    void saveRole(RoleDTO roleDTO);

    List<RoleDTO> findAllRole();

    void updateRole(RoleDTO roleDTO);

    void changeRole(long roleId);

    List<RoleDTO> filterRole(String roleName);








}
