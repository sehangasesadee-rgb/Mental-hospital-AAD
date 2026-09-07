package lk.ijse.mental_hospital.service.impl;

import lk.ijse.mental_hospital.dto.PatientDTO;
import lk.ijse.mental_hospital.entity.Patient;
import lk.ijse.mental_hospital.enumaration.patientStatus;
import lk.ijse.mental_hospital.repository.PatientRepository;
import lk.ijse.mental_hospital.service.PatientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@Slf4j
public class PatientServiceImpl implements PatientService {
    private final PatientRepository patientRepository;
    public PatientServiceImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }
    @Override
    public void savePatient(PatientDTO patientDTO) {
        log.info("Saving patient");
        try {
            Patient patient = new Patient();
            patient.setPatientName(patientDTO.getPatientName());
            patient.setDateOfBirth(patientDTO.getDateOfBirth());
            patient.setGender(patientDTO.getGender());
            patient.setAddress(patientDTO.getAddress());
            patient.setContactNumber(patientDTO.getContactNumber());
            patient.setEmail(patientDTO.getEmail());
            patientRepository.save(patient);

        }catch (Exception e){
            log.error("Error saving patient");
        }

    }

    @Override
    public List<PatientDTO> getPatients() {
        try {
            List<PatientDTO> patientDTOList = new ArrayList<>();
            List<Patient> patients = patientRepository.findAll();
            for (Patient patient : patients) {
                PatientDTO patientDTO = new PatientDTO();
                patientDTO.setPatientId(patient.getPatientId());
                patientDTO.setPatientName(patient.getPatientName());
                patientDTO.setDateOfBirth(patient.getDateOfBirth());
                patientDTO.setGender(patient.getGender());
                patientDTO.setAddress(patient.getAddress());
                patientDTO.setContactNumber(patient.getContactNumber());
                patientDTO.setEmail(patient.getEmail());
                patientDTO.setPatientStatus(patient.getPatientStatus());
                patientDTOList.add(patientDTO);
            }
            return patientDTOList;
        }catch (Exception e){
            log.error("Error getting patients");
            throw e;
        }
    }

    @Override
    public void updatePatient(PatientDTO patientDTO) {
        log.info("Updating patient");
        try {
            Optional<Patient> optionalPatient = patientRepository.findById(patientDTO.getPatientId());
            if (optionalPatient.isEmpty()){
                throw new Exception("patient not found");
            }
            Patient patient = optionalPatient.get();
            patient.setPatientName(patientDTO.getPatientName());
            patient.setDateOfBirth(patientDTO.getDateOfBirth());
            patient.setGender(patientDTO.getGender());
            patient.setAddress(patientDTO.getAddress());
            patient.setContactNumber(patientDTO.getContactNumber());
            patient.setEmail(patientDTO.getEmail());
            patient.setPatientStatus(patientDTO.getPatientStatus());
            patientRepository.save(patient);
        }catch (Exception e){
            log.error("Error getting patient");
        }
    }

    @Override
    public void changePatientStatus(long id) {
        log.info("Deleting patient");
        try {
            Optional<Patient> optionalPatient = patientRepository.findById(id);
            if (optionalPatient.isEmpty()){
                throw new RuntimeException("patient not found");
            }
            Patient patient = optionalPatient.get();
//            patient.setpatientStatus(patientStatus.INACTIVE);
            if(patient.getPatientStatus().equals("INACTIVE")){
                patient.setPatientStatus("ACTIVE");
            }else {
                patient.setPatientStatus("INACTIVE");
            }
            patientRepository.save(patient);
        }catch (Exception e){
            log.error("Error getting patient");
        }
    }

    @Override
    public List<PatientDTO> filterPatientsByName(String name) {
        try{
            List<PatientDTO> patientDTOList = new ArrayList<>();
            List<Patient> patients = patientRepository.filterPatient(name);
            for (Patient patient : patients) {
                PatientDTO patientDTO = new PatientDTO(patient.getPatientId(),patient.getPatientName(),patient.getDateOfBirth(),patient.getGender(),patient.getContactNumber(),patient.getAddress(),patient.getEmail(),patient.getPatientStatus());
                patientDTOList.add(patientDTO);
            }
            return patientDTOList;
        }catch (Exception e){
            log.error("Error getting patients");
            throw e;
        }

    }


}
