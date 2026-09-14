package lk.ijse.mental_hospital.service;

import lk.ijse.mental_hospital.dto.PrescriptionDTO;
import lk.ijse.mental_hospital.entity.Prescription;

import java.util.List;

public interface PrescriptionService {

    void addPrescription(PrescriptionDTO prescriptionDTO);
    List<PrescriptionDTO> getAllPrescriptions();

    void updatePrescription(PrescriptionDTO prescriptionDTO);

    List<PrescriptionDTO> filterPrescription(Long id);
}
