package lk.ijse.mental_hospital.service.impl;

import lk.ijse.mental_hospital.dto.MedicationDTO;
import lk.ijse.mental_hospital.entity.Medication;
import lk.ijse.mental_hospital.repository.MedicationRepository;
import lk.ijse.mental_hospital.service.MedicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class MedicationServiceImpl implements MedicationService {
    private final MedicationRepository medicationRepository;
    public MedicationServiceImpl(MedicationRepository medicationRepository) {
        this.medicationRepository = medicationRepository;
    }
    @Override
    public void addMedication(MedicationDTO medicationDTO) {
        log.info("Adding medication");
        try {
            Medication medication = new Medication();
            medication.setMedicationName(medicationDTO.getMedicationName());
            medication.setDosage(medicationDTO.getDosage());
            medication.setDescription(medicationDTO.getDescription());
            medication.setQuantity(medicationDTO.getQuantity());
            medication.setMedicationStatus(medicationDTO.getMedicationStatus());
            medicationRepository.save(medication);
        }catch (Exception e){
            log.error("addMedication error");
        }



    }

    @Override
    public List<MedicationDTO> getMedications() {
        try {
//            List<Medication> medications = medicationRepository.findAll();
            List<MedicationDTO> medicationDTOs = new ArrayList<>();
            List<Medication> medications = medicationRepository.findAll();
            for (Medication medication : medications) {
                MedicationDTO medicationDTO = new MedicationDTO();
                medicationDTO.setMedicationId(medication.getMedicationId());
                medicationDTO.setMedicationName(medication.getMedicationName());
                medicationDTO.setDosage(medication.getDosage());
                medicationDTO.setDescription(medication.getDescription());
                medicationDTO.setQuantity(medication.getQuantity());
                medicationDTO.setMedicationStatus(medication.getMedicationStatus());
                medicationDTOs.add(medicationDTO);
            }
            return medicationDTOs;
        }catch (Exception e){
            log.error("getMedications error");
            throw e;
        }
    }

    @Override
    public void updateMedication(MedicationDTO medicationDTO) {
        log.info("Updating medication");
        try {
            Optional<Medication> medication = medicationRepository.findById(medicationDTO.getMedicationId());
            if(medication.isEmpty()){
                log.error("medication not found");
                throw new Exception("medication not found");

            }
            Medication medication1 = medication.get();
//            medication1.setMedicationId(medicationDTO.getMedicationId());
            medication1.setMedicationName(medicationDTO.getMedicationName());
            medication1.setDosage(medicationDTO.getDosage());
            medication1.setDescription(medicationDTO.getDescription());
            medication1.setQuantity(medicationDTO.getQuantity());
            medication1.setMedicationStatus(medicationDTO.getMedicationStatus());
            medicationRepository.save(medication1);


        }catch (Exception e){
            log.error("updateMedication error");
        }
    }

    @Override
    public List<MedicationDTO> filterMedication(Long id) {
        try {
            List<MedicationDTO> medicationDTOs = new ArrayList<>();
            List<Medication> medications = medicationRepository.filterMedication(id);
            for (Medication medication : medications) {
                MedicationDTO medicationDTO = new MedicationDTO(
                        medication.getMedicationId(),
                        medication.getMedicationName(),
                        medication.getDosage(),
                        medication.getDescription(),
                        medication.getQuantity(),
                        medication.getMedicationStatus()
                );
                medicationDTOs.add(medicationDTO);
            }
            return medicationDTOs;

        }catch (Exception e){
            log.error("filterMedication error");
            throw e;
        }


        ///  ///////delete ek na/////////////////////////////
    }
}
