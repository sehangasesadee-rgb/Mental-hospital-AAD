package lk.ijse.mental_hospital.service.impl;

import lk.ijse.mental_hospital.dto.DepartmentDTO;
import lk.ijse.mental_hospital.entity.Department;
import lk.ijse.mental_hospital.enumaration.DepartmentStatus;
import lk.ijse.mental_hospital.repository.DepartmentRepository;
import lk.ijse.mental_hospital.service.DepartmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


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

    @Override
    public void updateDepartment(DepartmentDTO departmentDTO) {
        log.info("Updating Department");
        try {
            Optional<Department> departmentOptional = departmentRepository.findById(departmentDTO.getDepartmentId());
            if(departmentOptional.isEmpty()){
                throw new Exception("department not found");
            }
            Department department = departmentOptional.get();
            department.setDepartmentName(departmentDTO.getDepartmentName());
            department.setDescription(departmentDTO.getDescription());
            department.setDepartmentStatus(departmentDTO.getDepartmentStatus());
            departmentRepository.save(department);

        } catch (Exception e) {
            log.info("Department not saved");
        }
    }

//    @Override
//    public void changeDepartment(Long id) {
//        log.info("Changing Department");
//        try {
//            Optional<Department> departmentOptional = departmentRepository.findById(id);
//            if(departmentOptional.isEmpty()){
//                throw new RuntimeException("department not found");
//            }
//            Department department1 = departmentOptional.get();
//            department1.setDepartmentStatus(DepartmentStatus.INACTIVE);
////            department1.setDepartmentStatus(DepartmentStatus.INACTIVE);
//            departmentRepository.save(departmentOptional.get());
//        }catch (Exception e){
//            log.info("Department not saved");
//        }
//    }

@Override
public void changeDepartment(Long id) {
    log.info("Changing Department");

    try {
        Optional<Department> departmentOptional =
                departmentRepository.findById(id);

        if (departmentOptional.isEmpty()) {
            throw new RuntimeException("Department not found");
        }

        Department department = departmentOptional.get();

        department.setDepartmentStatus(
                DepartmentStatus.INACTIVE.name()
        );

        departmentRepository.save(department);

    } catch (Exception e) {
        log.error("Department status change failed", e);
        throw e;
    }
}

    @Override
    public List<DepartmentDTO> filterDepartment(String departmentName) {
        try{
            List<DepartmentDTO> department = new ArrayList<>();
            List<Department> departments = departmentRepository.filterDepartment(departmentName);

            for(Department department1 : departments){
                DepartmentDTO departmentDTO = new DepartmentDTO(department1.getDepartmentId(),department1.getDepartmentName(),department1.getDescription(),department1.getDepartmentStatus());
                department.add(departmentDTO);

            }

            return department;

        } catch (Exception e) {
            log.info("Department not found");
            throw e;
        }
    }


}
