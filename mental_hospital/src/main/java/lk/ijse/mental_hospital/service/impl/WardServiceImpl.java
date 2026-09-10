package lk.ijse.mental_hospital.service.impl;

import lk.ijse.mental_hospital.constant.CommonResponse;
import lk.ijse.mental_hospital.dto.WardDTO;
import lk.ijse.mental_hospital.entity.Department;
import lk.ijse.mental_hospital.entity.Ward;
import lk.ijse.mental_hospital.enumaration.WardStatus;
import lk.ijse.mental_hospital.repository.DepartmentRepository;
import lk.ijse.mental_hospital.repository.WardRepository;
import lk.ijse.mental_hospital.service.WardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@Slf4j
public class WardServiceImpl implements WardService {
    private final WardRepository wardRepository;
    private final DepartmentRepository departmentRepository;
    public WardServiceImpl(WardRepository wardRepository, DepartmentRepository departmentRepository) {
        this.wardRepository = wardRepository;
        this.departmentRepository = departmentRepository;
    }
    @Override
    public void saveWard(WardDTO wardDTO) {
        log.info("Saving Ward Details");
        try {
            Optional<Department> optionalDepartment = departmentRepository.findById(wardDTO.getDepartmentId());
            if (optionalDepartment.isEmpty()) {
                throw new Exception("Department Not Found");
            }

            Ward ward = new Ward();
            ward.setWardName(wardDTO.getWardName());
            ward.setWardType(wardDTO.getWardType());
            ward.setCapacity(wardDTO.getCapacity());
            ward.setDepartment(optionalDepartment.get());
            wardRepository.save(ward);

        }catch (Exception e) {
            log.info("Ward Save Failed");
        }
    }

    @Override
    public List<WardDTO> getAllWards() {
        try {
            List<WardDTO> wardDTOS = new ArrayList<>();
            List<Ward> wards = wardRepository.findAll();
            for (Ward ward : wards) {
                WardDTO wardDTO = new WardDTO();
                wardDTO.setWardId(ward.getWardId());
                wardDTO.setWardName(ward.getWardName());
                wardDTO.setWardType(ward.getWardType());
                wardDTO.setCapacity(ward.getCapacity());
                //department ek demme ne
                wardDTO.setWardStatus(ward.getWardStatus());
                wardDTO.setDepartmentId(ward.getDepartment().getDepartmentId());
                wardDTOS.add(wardDTO);
            }
            return wardDTOS;

        }catch (Exception e) {
            log.info("Ward Load Failed");
            throw e;
        }
    }

    @Override
    public void updateWard(WardDTO wardDTO) {
        log.info("Updating Ward Details");
        try {
            Optional<Department> optionalDepartment = departmentRepository.findById(wardDTO.getDepartmentId());
            if (optionalDepartment.isEmpty()) {
                throw new Exception("Department Not Found");
            }
            Department department = optionalDepartment.get();

            Optional<Ward> optionalWard = wardRepository.findById(wardDTO.getWardId());
            if (optionalWard.isEmpty()) {
                throw new Exception("Ward not found");
            }
            Ward ward = optionalWard.get();
            ward.setWardName(wardDTO.getWardName());
            ward.setWardType(wardDTO.getWardType());
            ward.setCapacity(wardDTO.getCapacity());
            ward.setDepartment(department);
            ward.setWardStatus(wardDTO.getWardStatus());
            wardRepository.save(ward);
        }catch (Exception e) {
            log.info("Ward Update Failed");
        }
    }

    @Override
    public void changeWardStatus(long id) {
        log.info("Changing Ward Status Details");
        try {
            Optional<Ward> optionalWard = wardRepository.findById(id);
            if (optionalWard.isEmpty()) {
                throw new RuntimeException("Ward not found");
            }
           Ward ward = optionalWard.get();
            ward.setWardStatus(WardStatus.INACTIVE);
            wardRepository.save(ward);

        }catch (Exception e){
            log.info("Ward Change Failed");
        }

    }

    @Override
    public List<WardDTO> filterWard(String name) {
        try {
            List<WardDTO> wardDTOS = new ArrayList<>();
            List<Ward> wards = wardRepository.filterWard(name);
            for (Ward ward : wards) {
                WardDTO wardDTO = new WardDTO(
                        ward.getWardId(),
                        ward.getWardName(),
                        ward.getWardType(),
                        ward.getCapacity(),
                        ward.getDepartment().getDepartmentId(),
                        ward.getWardStatus()
                );
                wardDTOS.add(wardDTO);
            }
            return wardDTOS;
        }catch (Exception e){
            log.info("Ward Load Failed");
            throw e;
        }
    }
}
