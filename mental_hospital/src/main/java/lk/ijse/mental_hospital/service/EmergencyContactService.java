package lk.ijse.mental_hospital.service;

import lk.ijse.mental_hospital.dto.EmergencyContactDTO;
import lk.ijse.mental_hospital.entity.EmergencyContact;

import java.util.List;

public interface EmergencyContactService {

    void saveEmergencyContact(EmergencyContactDTO emergencyContactDTO);

    List<EmergencyContactDTO> getAllEmergencyContactId();

    void UpdateEmergencyContact(EmergencyContactDTO emergencyContactDTO);

    List<EmergencyContactDTO> filterEmergencyContact(Long id);



}
