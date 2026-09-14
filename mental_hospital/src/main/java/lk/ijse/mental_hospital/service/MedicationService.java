package lk.ijse.mental_hospital.service;

import lk.ijse.mental_hospital.dto.MedicationDTO;
import lk.ijse.mental_hospital.entity.Medication;

import java.util.List;

public interface MedicationService {

    void addMedication(MedicationDTO medicationDTO);

    List<MedicationDTO> getMedications();

    void updateMedication(MedicationDTO medicationDTO);
    List<MedicationDTO> filterMedication(Long id);
}
