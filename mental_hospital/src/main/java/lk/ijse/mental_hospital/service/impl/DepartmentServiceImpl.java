package lk.ijse.mental_hospital.service.impl;

import lk.ijse.mental_hospital.dto.DepartmentDTO;
import lk.ijse.mental_hospital.entity.Department;
import lk.ijse.mental_hospital.repository.DepartmentRepository;
import lk.ijse.mental_hospital.service.DepartmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;
    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public void saveDepartment(DepartmentDTO departmentDTO) {
        log.info("Saving Department");
        try {
            Department department = new Department();
            department.setDepartmentName(departmentDTO.getDepartmentName());
            department.setDescription(departmentDTO.getDescription());
//            department.getDepartmentStatus(departmentDTO.getDepartmentStatus());
            department.setDepartmentStatus(departmentDTO.getDepartmentStatus());
            departmentRepository.save(department);
        }catch (Exception e){
            log.info("Department not saved");
        }
    }
}
