package lk.ijse.mental_hospital.service;

import lk.ijse.mental_hospital.dto.DepartmentDTO;
import lk.ijse.mental_hospital.entity.Department;

import java.util.List;

public interface DepartmentService {
    void saveDepartment(DepartmentDTO departmentDTO);

    List<DepartmentDTO> getAllDepartments();
}
