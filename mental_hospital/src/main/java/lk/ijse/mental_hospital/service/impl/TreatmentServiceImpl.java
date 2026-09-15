package lk.ijse.mental_hospital.service.impl;

import lk.ijse.mental_hospital.dto.TreatmentDTO;
import lk.ijse.mental_hospital.entity.Doctor;
import lk.ijse.mental_hospital.entity.Patient;
import lk.ijse.mental_hospital.entity.Treatment;
import lk.ijse.mental_hospital.repository.DoctorRepository;
import lk.ijse.mental_hospital.repository.PatientRepository;
import lk.ijse.mental_hospital.repository.TreatmentRepository;
import lk.ijse.mental_hospital.service.TreatmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class TreatmentServiceImpl implements TreatmentService {
    private final TreatmentRepository treatmentRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    public TreatmentServiceImpl(TreatmentRepository treatmentRepository, PatientRepository patientRepository, DoctorRepository doctorRepository) {
        this.treatmentRepository = treatmentRepository;
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
    }

    @Override
    public void addTreatment(TreatmentDTO treatmentDTO) {

        Optional<Patient> optionalPatient = patientRepository.findById(treatmentDTO.getPatientId());
        if (optionalPatient.isEmpty()){
            throw new RuntimeException("patient not found");
        }
        Optional<Doctor> optionalDoctor = doctorRepository.findById(treatmentDTO.getDoctorId());
        if (optionalDoctor.isEmpty()){
            throw new RuntimeException("doctor not found");
        }

        Treatment treatment = new Treatment();
        treatment.setTreatmentName(treatmentDTO.getTreatmentName());
        treatment.setDescription(treatmentDTO.getDescription());
        treatment.setTreatmentDate(treatmentDTO.getTreatmentDate());
        treatment.setTreatmentStatus(treatmentDTO.getTreatmentStatus());
        treatment.setPatient(optionalPatient.get());
        treatment.setDoctor(optionalDoctor.get());
        treatmentRepository.save(treatment);

    }

    @Override
    public List<TreatmentDTO> findAllTreatment() {
        try {
            List<Treatment> treatments = treatmentRepository.findAll();
            List<TreatmentDTO> treatmentDTOs = new ArrayList<>();
            for (Treatment treatment : treatments) {
                TreatmentDTO treatmentDTO = new TreatmentDTO();
                treatmentDTO.setTreatmentId(treatment.getTreatmentId());
                treatmentDTO.setTreatmentName(treatment.getTreatmentName());
                treatmentDTO.setDescription(treatment.getDescription());
                treatmentDTO.setTreatmentDate(treatment.getTreatmentDate());
                treatmentDTO.setTreatmentStatus(treatment.getTreatmentStatus());
                treatmentDTO.setPatientId(treatment.getPatient().getPatientId());
                treatmentDTO.setDoctorId(treatment.getDoctor().getDoctorId());

                treatmentDTOs.add(treatmentDTO);
            }
            return treatmentDTOs;
        }catch (Exception e){
            log.error("Error in findAllTreatment");
            throw e;
        }
    }

    @Override
    public void updateTreatment(TreatmentDTO treatmentDTO) {
        try {
            Optional<Treatment> optional = treatmentRepository.findById(treatmentDTO.getTreatmentId());
            if (optional.isEmpty()){
                throw new RuntimeException("treatment not found");
            }

            Optional<Patient> optionalPatient = patientRepository.findById(treatmentDTO.getPatientId());
            if (optionalPatient.isEmpty()){
                throw new RuntimeException("patient not found");
            }
            Optional<Doctor> optionalDoctor = doctorRepository.findById(treatmentDTO.getDoctorId());
            if (optionalDoctor.isEmpty()){
                throw new RuntimeException("doctor not found");
            }
            Treatment treatment = new Treatment();
            treatment.setTreatmentId(treatmentDTO.getTreatmentId());
            treatment.setTreatmentName(treatmentDTO.getTreatmentName());
            treatment.setDescription(treatmentDTO.getDescription());
            treatment.setTreatmentDate(treatmentDTO.getTreatmentDate());
            treatment.setTreatmentStatus(treatmentDTO.getTreatmentStatus());
            treatment.setPatient(optionalPatient.get());
            treatment.setDoctor(optionalDoctor.get());

            treatmentRepository.save(treatment);

        } catch (Exception e) {
            log.error("Error in updateTreatment");
            throw e;
        }
    }

    @Override
    public List<TreatmentDTO> filterTreatments(Long id) {
        try {
            List<TreatmentDTO> treatmentDTOs = new ArrayList<>();
            List<Treatment> treatments = treatmentRepository.filterTreatments(id);
            for (Treatment treatment : treatments) {
                TreatmentDTO treatmentDTO = new TreatmentDTO(
                        treatment.getTreatmentId(),
                        treatment.getTreatmentName(),
                        treatment.getDescription(),
                        treatment.getTreatmentDate(),
                        treatment.getTreatmentStatus(),
                        treatment.getPatient().getPatientId(),
                        treatment.getDoctor().getDoctorId()

                );
                treatmentDTOs.add(treatmentDTO);
            }
            return treatmentDTOs;
        }catch (Exception e){
            log.error("Error in findAllTreatment");
            throw e;
        }
    }
}
