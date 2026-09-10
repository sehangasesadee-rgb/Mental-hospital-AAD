package lk.ijse.mental_hospital.service;

import lk.ijse.mental_hospital.dto.DoctorDTO;
import lk.ijse.mental_hospital.entity.Doctor;
import lk.ijse.mental_hospital.enumaration.DoctorSpecialization;

import java.util.List;


public interface DoctorService {
    void saveDoctor(DoctorDTO doctorDTO);

    List<DoctorDTO> getAllDoctor();

    void updateDoctor(DoctorDTO doctorDTO);

    void ChangeDoctorStatus(long id);

    List<DoctorDTO> filterDoctor(String name);
}
