package lk.ijse.mental_hospital.service;

import lk.ijse.mental_hospital.dto.NurseDTO;

import java.util.List;

public interface NurseService {

    void saveNurse(NurseDTO nurseDTO);

    List<NurseDTO> findAllNurse();

    void updateNurse(NurseDTO nurseDTO);

    void changeNurseStatus(Long id);


    List<NurseDTO> filterNurse(String nurseName);
}
