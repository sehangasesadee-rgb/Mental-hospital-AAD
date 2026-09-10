package lk.ijse.mental_hospital.service;

import lk.ijse.mental_hospital.dto.DepartmentDTO;
import lk.ijse.mental_hospital.dto.UserDTO;
import lk.ijse.mental_hospital.entity.Department;

import java.util.List;

public interface DepartmentService {
    void saveDepartment(DepartmentDTO departmentDTO);

    List<DepartmentDTO> getAllDepartments();

    void updateDepartment(DepartmentDTO departmentDTO);

    void changeDepartment(Long id);

    List<DepartmentDTO> filterDepartment(String name);

}
