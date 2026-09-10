package lk.ijse.mental_hospital.service.impl;
import lk.ijse.mental_hospital.entity.Nurse;

import lk.ijse.mental_hospital.dto.NurseDTO;
import lk.ijse.mental_hospital.entity.Department;
import lk.ijse.mental_hospital.entity.Ward;
import lk.ijse.mental_hospital.enumaration.NurseStatus;
import lk.ijse.mental_hospital.repository.DepartmentRepository;
import lk.ijse.mental_hospital.repository.NurseRepository;
import lk.ijse.mental_hospital.repository.WardRepository;
import lk.ijse.mental_hospital.service.NurseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@Slf4j
@RequiredArgsConstructor
public class NurseServiceImpl implements NurseService {
    private final NurseRepository nurseRepository;
    private final WardRepository wardRepository;
    private final DepartmentRepository departmentRepository;


    @Override
    public void saveNurse(NurseDTO nurseDTO) {
        log.info("saveNurse");

        Optional<Ward> optionalWard =
                wardRepository.findById(nurseDTO.getWardId());

        if (optionalWard.isEmpty()) {
            throw new RuntimeException("Ward not found");
        }

        Optional<Department> optionalDepartment =
                departmentRepository.findById(nurseDTO.getDepartmentId());

        if (optionalDepartment.isEmpty()) {
            throw new RuntimeException("Department not found");
        }

        Nurse nurse = new Nurse();

        nurse.setNurseName(nurseDTO.getNurseName());
        nurse.setNurseStatus(nurseDTO.getNurseStatus());
        nurse.setContactNumber(nurseDTO.getContactNumber());
        nurse.setEmail(nurseDTO.getEmail());

        nurse.setWard(optionalWard.get());
        nurse.setDepartment(optionalDepartment.get());

        nurseRepository.save(nurse);

        log.info("Nurse saved successfully");
    }


    @Override
    public List<NurseDTO> findAllNurse() {

        List<Nurse> nurses = nurseRepository.findAll();

        return nurses.stream().map(nurse -> new NurseDTO(nurse.getNurseId(),
                nurse.getNurseName(),
                nurse.getEmail(),
                nurse.getContactNumber(),
                nurse.getDepartment().getDepartmentId(),
                nurse.getWard().getWardId(),
                nurse.getNurseStatus())).toList();
    }

    @Override
    public void updateNurse(NurseDTO nurseDTO) {

        Optional<Ward> optionalWard =
                wardRepository.findById(nurseDTO.getWardId());

        if (optionalWard.isEmpty()) {
            throw new RuntimeException("Ward not found");
        }

        Optional<Department> optionalDepartment =
                departmentRepository.findById(nurseDTO.getDepartmentId());

        if (optionalDepartment.isEmpty()) {
            throw new RuntimeException("Department not found");
        }

        Optional<Nurse> optionalNurse = nurseRepository.findById(nurseDTO.getNurseId());
        if (optionalNurse.isEmpty()) {
            throw new RuntimeException("Nurse not found");
        }

        Nurse nurse = optionalNurse.get();
        nurse.setNurseName(nurseDTO.getNurseName());
        nurse.setNurseStatus(nurseDTO.getNurseStatus());
        nurse.setContactNumber(nurseDTO.getContactNumber());
        nurse.setEmail(nurseDTO.getEmail());
        nurse.setWard(optionalWard.get());
        nurse.setDepartment(optionalDepartment.get());
        nurseRepository.save(nurse);

    }

    @Override
    public void changeNurseStatus(Long id) {
        Optional<Nurse> optionalNurse = nurseRepository.findById(id);
        if (optionalNurse.isEmpty()) {
            throw new RuntimeException("Nurse not found");
        }
        Nurse nurse = optionalNurse.get();
        if (nurse.getNurseStatus() == NurseStatus.ACTIVE){
            nurse.setNurseStatus(NurseStatus.INACTIVE);
        }else  {
            nurse.setNurseStatus(NurseStatus.ACTIVE);
        }

        nurseRepository.save(nurse);
    }

    @Override
    public List<NurseDTO> filterNurse(String nurseName) {
        List<Nurse> nurses = nurseRepository.findAllByNurseName(nurseName);

        return nurses.stream().map(nurse -> new NurseDTO(nurse.getNurseId(),
                nurse.getNurseName(),
                nurse.getEmail(),
                nurse.getContactNumber(),
                nurse.getDepartment().getDepartmentId(),
                nurse.getWard().getWardId(),
                nurse.getNurseStatus())).toList();

    }
}
