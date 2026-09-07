package lk.ijse.mental_hospital.service.impl;

import lk.ijse.mental_hospital.dto.DoctorDTO;
import lk.ijse.mental_hospital.entity.Doctor;
import lk.ijse.mental_hospital.repository.DoctorRepository;
import lk.ijse.mental_hospital.service.DoctorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DoctorServiceImpl implements DoctorService {
    private final DoctorRepository doctorRepository;
    public DoctorServiceImpl(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }


//    @Override
//    public void saveDoctor(DoctorDTO doctorDTO) {
//        log.info("DoctorDTO");
//        try{
//            Doctor doctor = new Doctor();
//            doctor.setDoctorName(doctorDTO.getDoctorName());
//            doctor.setEmail(doctorDTO.getEmail());
//            doctor.setContactNumber(doctorDTO.getContactNumber());
//            doctor.setSpecialization( doctorDTO.getSpecialization() );
//
//        }

//    }
}
