package lk.ijse.mental_hospital.service;

import lk.ijse.mental_hospital.dto.AdmissionDTO;
import lk.ijse.mental_hospital.entity.Admission;

import java.util.List;

public interface AdmissionService {

    void saveAdmission(AdmissionDTO admissionDTO);
    List<AdmissionDTO> findAllAdmissions();

    void updateAdmission(AdmissionDTO admissionDTO);
}
