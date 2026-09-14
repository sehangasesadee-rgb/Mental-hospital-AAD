package lk.ijse.mental_hospital.service.impl;

import lk.ijse.mental_hospital.dto.DiagnosisDTO;
import lk.ijse.mental_hospital.entity.Diagnosis;
import lk.ijse.mental_hospital.entity.Doctor;
import lk.ijse.mental_hospital.entity.Patient;
import lk.ijse.mental_hospital.repository.DiagnosisRepository;
import lk.ijse.mental_hospital.repository.DoctorRepository;
import lk.ijse.mental_hospital.repository.PatientRepository;
import lk.ijse.mental_hospital.service.DiagnosisService;
import lk.ijse.mental_hospital.service.DoctorService;
import lk.ijse.mental_hospital.service.PatientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class DiagnosisServiceImpl implements DiagnosisService {
    private final DiagnosisRepository diagnosisRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    public DiagnosisServiceImpl(DiagnosisRepository diagnosisRepository, PatientRepository patientRepository, DoctorRepository doctorRepository) {
        this.diagnosisRepository = diagnosisRepository;
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
    }

    @Override
    public void saveDiagnosis(DiagnosisDTO diagnosisDTO) {
        log.info("DiagnosisServiceImpl saveDiagnosis");

//        Optional<Diagnosis> optionalDiagnosis = diagnosisRepository.findById(diagnosisDTO.getDiagnosisId());
//        if (optionalDiagnosis.isEmpty()) {
//            throw new RuntimeException("Diagnosis not found");
//        }
        Optional<Patient> patientOptional = patientRepository.findById(diagnosisDTO.getPatientId());
        if (patientOptional.isEmpty()) {
            throw new RuntimeException("Patient is not found");
        }
        Optional<Doctor> doctorOptional = doctorRepository.findById(diagnosisDTO.getDoctorId());
        if (doctorOptional.isEmpty()) {
            throw new RuntimeException("Doctor is not found");
        }
        Diagnosis diagnosis = new Diagnosis();
        diagnosis.setDiagnosisName(diagnosisDTO.getDiagnosisName());
        diagnosis.setDescription(diagnosisDTO.getDescription());
        diagnosis.setDiagnosisDate(diagnosisDTO.getDiagnosisDate());
        diagnosis.setPatient(patientOptional.get());
        diagnosis.setDoctor(doctorOptional.get());
        diagnosisRepository.save(diagnosis);
    }

    @Override
    public List<DiagnosisDTO> getGetAllDiagnosis() {
        try {
            List<Diagnosis> diagnosisList = diagnosisRepository.findAll();
            List<DiagnosisDTO> diagnosisDTOList = new ArrayList<>();
            for (Diagnosis diagnosis : diagnosisList) {
                DiagnosisDTO diagnosisDTO = new DiagnosisDTO();
                diagnosisDTO.setDiagnosisId(diagnosis.getDiagnosisId());
                diagnosisDTO.setDiagnosisName(diagnosis.getDiagnosisName());
                diagnosisDTO.setDescription(diagnosis.getDescription());
                diagnosisDTO.setDiagnosisDate(diagnosis.getDiagnosisDate());
                diagnosisDTO.setDoctorId(diagnosis.getDoctor().getDoctorId());
                diagnosisDTO.setPatientId(diagnosis.getPatient().getPatientId());
                diagnosisDTOList.add(diagnosisDTO);
            }
            return diagnosisDTOList;
        }catch (Exception e){
            log.error("getAllDiagnosis");
            throw e;
        }
    }

    @Override
    public void updateDiagnosis(DiagnosisDTO diagnosisDTO) {
        log.info("DiagnosisServiceImpl updateDiagnosis");
        try {
            Optional<Patient> patientOptional = patientRepository.findById(diagnosisDTO.getPatientId());
            if (patientOptional.isEmpty()) {
                throw new RuntimeException("Patient is not found");
            }
            Optional<Doctor> doctorOptional = doctorRepository.findById(diagnosisDTO.getDoctorId());
            if (doctorOptional.isEmpty()) {
                throw new RuntimeException("Doctor is not found");
            }
            Diagnosis diagnosis = new Diagnosis();
            diagnosis.setDiagnosisId(diagnosisDTO.getDiagnosisId());
            diagnosis.setDiagnosisName(diagnosisDTO.getDiagnosisName());
            diagnosis.setDescription(diagnosisDTO.getDescription());
            diagnosis.setDiagnosisDate(diagnosisDTO.getDiagnosisDate());
            diagnosis.setPatient(patientOptional.get());
            diagnosis.setDoctor(doctorOptional.get());
            diagnosisRepository.save(diagnosis);
        } catch (Exception e) {
            log.info("updateDiagnosis Exception");
        }


    }

    @Override
    public List<DiagnosisDTO> filterDiagnosis(Long id) {
        try {
            List<DiagnosisDTO> diagnosisDTOList = new ArrayList<>();
            List<Diagnosis> diagnosisOptional = diagnosisRepository.filterDiagnosis(id);

            for (Diagnosis diagnosis : diagnosisOptional) {
                DiagnosisDTO diagnosisDTO = new DiagnosisDTO(
                        diagnosis.getDiagnosisId(),
                        diagnosis.getDiagnosisName(),
                        diagnosis.getDescription(),
                        diagnosis.getDiagnosisDate(),
                        diagnosis.getPatient().getPatientId(),
                        diagnosis.getDoctor().getDoctorId()
                );
                diagnosisDTOList.add(diagnosisDTO);
            }
            return diagnosisDTOList;
        }catch (Exception e){
            log.error("filterDiagnosis Exception");
            throw e;
        }
    }



    //delete ek kre ne


//    @Override
//    public void deleteDiagnosis(Long id) {
//        log.info("DiagnosisServiceImpl deleteDiagnosis");
//        try {
//            Optional<Diagnosis> diagnosisOptional = diagnosisRepository.findById(id);
//            if (diagnosisOptional.isEmpty()) {
//                throw new RuntimeException("Diagnosis is not found");
//            }
//            Optional<Doctor> doctorOptional = doctorRepository.findById(id);
//            if (doctorOptional.isEmpty()) {
//                throw new RuntimeException("Doctor is not found");
//            }
//            Optional<Patient> patientOptional = patientRepository.findById(id);
//            if (patientOptional.isEmpty()) {
//                throw new RuntimeException("Patient is not found");
//            }
//
//            Diagnosis diagnosis = diagnosisOptional.get();
//            diagnosis.setDiagnosisId(null);
//
//        }
//    }
}
