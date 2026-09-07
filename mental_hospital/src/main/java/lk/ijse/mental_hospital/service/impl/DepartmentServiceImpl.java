package lk.ijse.mental_hospital.service.impl;

import lk.ijse.mental_hospital.dto.DepartmentDTO;
import lk.ijse.mental_hospital.entity.Department;
import lk.ijse.mental_hospital.repository.DepartmentRepository;
import lk.ijse.mental_hospital.service.DepartmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


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

    @Override
    public List<DepartmentDTO> getAllDepartments() {
        try{
            List<DepartmentDTO> department = new ArrayList<>();
            List<Department> departments = departmentRepository.findAll();
            for(Department department1 : departments){
                DepartmentDTO departmentDTO = new DepartmentDTO();
                departmentDTO.setDepartmentId(department1.getDepartmentId());
                departmentDTO.setDepartmentName(department1.getDepartmentName());
                departmentDTO.setDescription(department1.getDescription());
                departmentDTO.setDepartmentStatus(department1.getDepartmentStatus());
                department.add(departmentDTO);

            }
            return department;
        }catch (Exception e){
            log.info("Department not saved");
            throw e;
        }

    }

}
