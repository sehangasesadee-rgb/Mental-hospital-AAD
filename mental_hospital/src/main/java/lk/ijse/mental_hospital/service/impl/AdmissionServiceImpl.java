package lk.ijse.mental_hospital.service.impl;

import lk.ijse.mental_hospital.dto.AdmissionDTO;
import lk.ijse.mental_hospital.entity.*;
import lk.ijse.mental_hospital.repository.*;
import lk.ijse.mental_hospital.service.AdmissionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@Slf4j

@RequiredArgsConstructor
public class AdmissionServiceImpl implements AdmissionService {
    private final AdmissionRepository admissionRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;
    private final BedRepository bedRepository;
    private final WardRepository wardRepository;
//    private final AdmissionService admissionService;


    @Override
    public void saveAdmission(AdmissionDTO admissionDTO) {
        log.info("Admission DTO");
//        Optional<Admission> optionaladmission = admissionRepository.findById(admissionDTO.getAdmissionId());
//        admissionRepository.findById(admissionDTO.getAdmissionId());
//
//        if (optionaladmission.isEmpty()){
//            throw new RuntimeException("Admission not found");
//        }
//
        Optional<Patient> optionalPatient = patientRepository.findById(admissionDTO.getPatientId());
        patientRepository.findById(admissionDTO.getPatientId());

        if (optionalPatient.isEmpty()){
            throw new RuntimeException("Patient not found");
        }

        Optional<Bed> optionalBed = bedRepository.findById(admissionDTO.getBedId());
        bedRepository.findById(admissionDTO.getBedId());

        if (optionalBed.isEmpty()){
            throw new RuntimeException("Bed not found");
        }
        Optional<Ward> OptionalWard = wardRepository.findById(admissionDTO.getWardId());
        if (OptionalWard.isEmpty()){
            throw new RuntimeException("Ward not found");
        }

        Optional<Doctor> optionalDoctor = doctorRepository.findById(admissionDTO.getDoctorId());
        if (optionalDoctor.isEmpty()){
            throw new RuntimeException("Doctor not found");
        }
        Admission admission = new  Admission();
//        admission.setAdmissionId(admissionDTO.getAdmissionId());
        admission.setAdmissionDate(admissionDTO.getAdmissionDate());
        admission.setReason(admissionDTO.getReason());
        admission.setAdmissionStatus(admissionDTO.getAdmissionStatus());
        admission.setPatient(optionalPatient.get());
        admission.setBed(optionalBed.get());
        admission.setWard(OptionalWard.get());
        admission.setDoctor(optionalDoctor.get());
        admissionRepository.save(admission);

    }

    @Override
    public List<AdmissionDTO> findAllAdmissions() {
        try {
            List<AdmissionDTO> admissionDTOList = new ArrayList<>();
            List<Admission> admissions = admissionRepository.findAll();
            for (Admission admission : admissions) {
                AdmissionDTO admissionDTO = new  AdmissionDTO();
                admissionDTO.setAdmissionId(admission.getAdmissionId());
                admissionDTO.setAdmissionDate(admission.getAdmissionDate());
                admissionDTO.setReason(admission.getReason());
                admissionDTO.setAdmissionStatus(admission.getAdmissionStatus());
                admissionDTO.setPatientId(admission.getPatient().getPatientId());
                admissionDTO.setWardId(admission.getWard().getWardId());
                admissionDTO.setBedId(admission.getBed().getBedId());
                admissionDTO.setDoctorId(admission.getDoctor().getDoctorId());
                admissionDTOList.add(admissionDTO);
            }return admissionDTOList;
        }catch (Exception e){
            log.error("Error in findAllAdmissions");
            throw e;
        }
    }

    @Override
    public void updateAdmission(AdmissionDTO admissionDTO) {
        try {
          Optional<Admission> optionalAdmission = admissionRepository.findById(admissionDTO.getAdmissionId());
          if(optionalAdmission.isEmpty()){
              throw new RuntimeException("Admission not found");
          }
          Optional<Patient> optionalPatient = patientRepository.findById(admissionDTO.getPatientId());
          if(optionalPatient.isEmpty()){
              throw new RuntimeException("Patient not found");
          }
          Optional<Bed> optionalBed = bedRepository.findById(admissionDTO.getBedId());
          if(optionalBed.isEmpty()){
              throw new RuntimeException("Bed not found");
          }
          Optional<Ward> optionalWard = wardRepository.findById(admissionDTO.getWardId());
          if(optionalWard.isEmpty()){
              throw new RuntimeException("Ward not found");
          }
          Optional<Doctor> optionalDoctor = doctorRepository.findById(admissionDTO.getDoctorId());
          if(optionalDoctor.isEmpty()){
              throw new RuntimeException("Doctor not found");
          }
          Admission admission = optionalAdmission.get();
          admission.setAdmissionDate(admissionDTO.getAdmissionDate());
          admission.setReason(admissionDTO.getReason());
          admission.setAdmissionStatus(admissionDTO.getAdmissionStatus());
          admission.setPatient(optionalPatient.get());
          admission.setBed(optionalBed.get());
          admission.setWard(optionalWard.get());
          admission.setDoctor(optionalDoctor.get());
          admissionRepository.save(admission);
        }catch (Exception e){
            log.error("Error in updateAdmission");
            throw e;
        }
    }

}
