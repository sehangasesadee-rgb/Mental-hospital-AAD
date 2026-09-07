package lk.ijse.mental_hospital.service;

import lk.ijse.mental_hospital.dto.PatientDTO;
import lk.ijse.mental_hospital.entity.Patient;

import java.util.List;

public interface PatientService {

    void savePatient(PatientDTO patientDTO);

    List<PatientDTO> getPatients();

    void updatePatient(PatientDTO patientDTO);

    void changePatientStatus(long id);

    List<PatientDTO> filterPatientsByName(String name);



}
