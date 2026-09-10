package lk.ijse.mental_hospital.service.impl;

import lk.ijse.mental_hospital.dto.DoctorDTO;
import lk.ijse.mental_hospital.entity.Department;
import lk.ijse.mental_hospital.entity.Doctor;
import lk.ijse.mental_hospital.enumaration.DoctorSpecialization;
import lk.ijse.mental_hospital.enumaration.DoctorStatus;
import lk.ijse.mental_hospital.repository.DepartmentRepository;
import lk.ijse.mental_hospital.repository.DoctorRepository;
import lk.ijse.mental_hospital.service.DoctorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class DoctorServiceImpl implements DoctorService {
    private final DoctorRepository doctorRepository;
    private final DepartmentRepository departmentRepository;
    public DoctorServiceImpl(DoctorRepository doctorRepository, DepartmentRepository departmentRepository) {
        this.doctorRepository = doctorRepository;
        this.departmentRepository = departmentRepository;
    }

    @Override
    public void saveDoctor(DoctorDTO doctorDTO) {
        log.info("Doctor service saveDoctor");
        try {

            Optional<Department> optionalDepartment = departmentRepository.findById(doctorDTO.getDepartmentId());
            if(optionalDepartment.isEmpty()){
                throw new RuntimeException("Department not found");
            }
            Department department = optionalDepartment.get();

            Doctor doctor = new Doctor();
            doctor.setDoctorName(doctorDTO.getDoctorName());
            doctor.setContactNumber(String.valueOf(doctorDTO.getContactNumber()));
            doctor.setEmail(doctorDTO.getEmail());
            doctor.setSpecialization(doctorDTO.getSpecialization());
            doctor.setDoctorStatus(doctor.getDoctorStatus());
            doctor.setDepartment(department);
            doctorRepository.save(doctor);

            }catch (Exception e){
            log.info("Doctor service saveDoctor Exception");

        }

    }

    @Override
    public List<DoctorDTO> getAllDoctor(){
        try {
            List<DoctorDTO> doctorDTOList = new ArrayList<>();
            List<Doctor> doctors =  doctorRepository.findAll();
            for(Doctor doctor : doctors){
                DoctorDTO doctorDTO = new DoctorDTO();
                doctorDTO.setDoctorId(doctor.getDoctorId());
                doctorDTO.setDoctorName(doctor.getDoctorName());
                doctorDTO.setContactNumber(doctor.getContactNumber());
                doctorDTO.setEmail(doctor.getEmail());
                doctorDTO.setSpecialization(doctor.getSpecialization());
                doctorDTO.setDoctorStatus(doctor.getDoctorStatus());
                doctorDTOList.add(doctorDTO);
            }
            return doctorDTOList;
        } catch (Exception e) {
            log.info("Doctor service getAllDoctor Exception");
            throw e;
        }
    }

    @Override
    public void updateDoctor(DoctorDTO doctorDTO) {
        log.info("Doctor service updateDoctor");
        try {
            Optional<Doctor>  optionalDoctor = doctorRepository.findById(doctorDTO.getDoctorId());
            if(optionalDoctor.isEmpty()){
                throw new RuntimeException("Doctor not found");
            }
            Doctor doctor = optionalDoctor.get();
            doctor.setDoctorName(doctorDTO.getDoctorName());
            doctor.setContactNumber(doctorDTO.getContactNumber());
            doctor.setEmail(doctorDTO.getEmail());
            doctor.setSpecialization(doctorDTO.getSpecialization());
            doctor.setDoctorStatus(doctorDTO.getDoctorStatus());
            doctorRepository.save(doctor);
        }catch (Exception e){
            log.info("Doctor service updateDoctor Exception");
        }
    }

    @Override
    public void ChangeDoctorStatus(long id) {
        log.info("Doctor service changeDoctorStatus");
        try {
            Optional<Doctor> optionalDoctor = doctorRepository.findById(id);
            if(optionalDoctor.isEmpty()){
                throw new RuntimeException("Doctor not found");
            }
            Doctor doctor = optionalDoctor.get();
            doctor.setDoctorStatus(DoctorStatus.INACTIVE);
            doctorRepository.save(doctor);
        }catch (Exception e){
            log.info("Doctor service changeDoctorStatus Exception");
        }
    }

    @Override
    public List<DoctorDTO> filterDoctor(String name) {
        try {
            List<DoctorDTO> doctorDTOList = new ArrayList<>();
            List<Doctor> doctors = doctorRepository.filterDoctor(name);

            for(Doctor doctor : doctors){
                DoctorDTO doctorDTO = new DoctorDTO(
                        doctor.getDoctorId(),
                        doctor.getDoctorName(),
                        doctor.getEmail(),
                        doctor.getContactNumber(),
                        doctor.getSpecialization(),
                        doctor.getDepartment().getDepartmentId(),
                        doctor.getDoctorStatus()
                );
                doctorDTOList.add(doctorDTO);
            }
            return doctorDTOList;
        } catch (Exception e) {
            log.info("Doctor service filterDoctor Exception");
            throw e;
        }
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
