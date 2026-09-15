package lk.ijse.mental_hospital.service.impl;

import lk.ijse.mental_hospital.dto.EmergencyContactDTO;
import lk.ijse.mental_hospital.entity.EmergencyContact;
import lk.ijse.mental_hospital.entity.Patient;
import lk.ijse.mental_hospital.repository.EmergencyContactRepository;
import lk.ijse.mental_hospital.repository.PatientRepository;
import lk.ijse.mental_hospital.repository.PrescriptionRepository;
import lk.ijse.mental_hospital.service.EmergencyContactService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class EmergencyContactServiceImpl implements EmergencyContactService {
    EmergencyContactRepository emergencyContactRepository;
    PatientRepository patientRepository;

    public EmergencyContactServiceImpl(EmergencyContactRepository emergencyContactRepository, PatientRepository patientRepository) {
        this.emergencyContactRepository = emergencyContactRepository;
        this.patientRepository = patientRepository;
    }

    @Override
    public void saveEmergencyContact(EmergencyContactDTO emergencyContactDTO) {
        Optional<Patient> patientOptional = patientRepository.findById(emergencyContactDTO.getPatientId());
        if (patientOptional.isEmpty()){
            throw new NullPointerException("Patient not found");
        }
        EmergencyContact emergencyContact = new EmergencyContact();
        emergencyContact.setPatient(patientOptional.get());
//        emergencyContact.setEmergencyContactId(emergencyContactDTO.getEmergencyContactId());
        emergencyContact.setContactName(emergencyContactDTO.getContactName());
        emergencyContact.setRelationship(emergencyContactDTO.getRelationship());
        emergencyContact.setContactNumber(emergencyContactDTO.getContactNumber());
        emergencyContact.setAddress(emergencyContactDTO.getAddress());
        emergencyContactRepository.save(emergencyContact);

    }

    @Override
    public List<EmergencyContactDTO> getAllEmergencyContactId() {
        try {
            List<EmergencyContact> emergencyContacts = emergencyContactRepository.findAll();
            List<EmergencyContactDTO> emergencyContactDTOs = new ArrayList<>();
            for (EmergencyContact emergencyContact : emergencyContacts) {
                EmergencyContactDTO emergencyContactDTO = new EmergencyContactDTO();
                emergencyContactDTO.setEmergencyContactId(emergencyContact.getEmergencyContactId());
                emergencyContactDTO.setPatientId(emergencyContact.getPatient().getPatientId());
                emergencyContactDTO.setContactName(emergencyContact.getContactName());
                emergencyContactDTO.setRelationship(emergencyContact.getRelationship());
                emergencyContactDTO.setContactNumber(emergencyContact.getContactNumber());
                emergencyContactDTO.setAddress(emergencyContact.getAddress());
                emergencyContactDTOs.add(emergencyContactDTO);

            }
            return  emergencyContactDTOs;

        }catch (Exception e){
            log.info("Error in getAllEmergencyContactId");
            throw e;
        }
    }

    @Override
    public void UpdateEmergencyContact(EmergencyContactDTO emergencyContactDTO) {
        try {
            Optional<EmergencyContact> optionalEmergencyContact = emergencyContactRepository.findById(emergencyContactDTO.getEmergencyContactId());
            if (optionalEmergencyContact.isEmpty()){
                throw new NullPointerException("EmergencyContact not found");
            }
            EmergencyContact emergencyContact = optionalEmergencyContact.get();

            emergencyContact.setContactName(emergencyContactDTO.getContactName());
            emergencyContact.setRelationship(emergencyContactDTO.getRelationship());
            emergencyContact.setContactNumber(emergencyContactDTO.getContactNumber());
            emergencyContact.setAddress(emergencyContactDTO.getAddress());
            emergencyContactRepository.save(emergencyContact);
        }catch (Exception e){
            log.info("Error in getAllEmergencyContactId");
            throw e;
        }
    }

    @Override
    public List<EmergencyContactDTO> filterEmergencyContact(Long id) {
        try {
            List<EmergencyContactDTO> emergencyContactDTOs = new ArrayList<>();
            List<EmergencyContact> emergencyContacts =  emergencyContactRepository.findEmergencyContactId(id);
            for (EmergencyContact emergencyContact : emergencyContacts) {
                EmergencyContactDTO emergencyContactDTO = new EmergencyContactDTO(
                        emergencyContact.getEmergencyContactId(),
                        emergencyContact.getContactName(),
                        emergencyContact.getRelationship(),
                        emergencyContact.getContactNumber(),
                        emergencyContact.getAddress(),
                        emergencyContact.getPatient().getPatientId()

                );
                emergencyContactDTOs.add(emergencyContactDTO);

            }
            return  emergencyContactDTOs;
        }catch (Exception e){
            log.info("Error in filterEmergencyContact");
            throw e;
        }
    }
}
