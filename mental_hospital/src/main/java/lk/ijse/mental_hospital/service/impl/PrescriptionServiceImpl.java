package lk.ijse.mental_hospital.service.impl;

import lk.ijse.mental_hospital.controller.PrescriptionController;
import lk.ijse.mental_hospital.dto.PrescriptionDTO;
import lk.ijse.mental_hospital.entity.Doctor;
import lk.ijse.mental_hospital.entity.Medication;
import lk.ijse.mental_hospital.entity.Patient;
import lk.ijse.mental_hospital.entity.Prescription;
import lk.ijse.mental_hospital.repository.DoctorRepository;
import lk.ijse.mental_hospital.repository.MedicationRepository;
import lk.ijse.mental_hospital.repository.PatientRepository;
import lk.ijse.mental_hospital.repository.PrescriptionRepository;
import lk.ijse.mental_hospital.service.PrescriptionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class PrescriptionServiceImpl implements PrescriptionService {
    private final PrescriptionRepository prescriptionRepository;
    private final MedicationRepository medicationRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;

    public PrescriptionServiceImpl(PrescriptionRepository prescriptionRepository,MedicationRepository medicationRepository, PatientRepository patientRepository, DoctorRepository doctorRepository) {
        this.prescriptionRepository = prescriptionRepository;
        this.medicationRepository = medicationRepository;
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
    }

    @Override
    public void addPrescription(PrescriptionDTO prescriptionDTO) {
        log.info("addPrescription");
        Optional<Doctor> optionalDoctor = doctorRepository.findById(prescriptionDTO.getDoctorId());
        if (optionalDoctor.isEmpty()) {
            throw new RuntimeException("Doctor Not Found");
        }

        Optional<Patient> optionalPatient = patientRepository.findById(prescriptionDTO.getPatientId());
        if (optionalPatient.isEmpty()) {
            throw new RuntimeException("Patient Not Found");
        }

        Optional<Medication> optionalMedication = medicationRepository.findById(prescriptionDTO.getMedicationId());
        if (optionalMedication.isEmpty()) {
            throw new RuntimeException("Medication Not Found");
        }
        Prescription prescription = new Prescription();
        prescription.setPrescriptionDate(prescriptionDTO.getPrescriptionDate());
        prescription.setDosage(prescriptionDTO.getDosage());
        prescription.setFrequency(prescriptionDTO.getFrequency());
        prescription.setInstructions(prescriptionDTO.getInstructions());
        prescription.setDurationDays(prescriptionDTO.getDurationDays());
        prescription.setDoctor(optionalDoctor.get());
        prescription.setPatient(optionalPatient.get());
        prescription.setMedication(optionalMedication.get());
        prescriptionRepository.save(prescription);

    }

    @Override
    public List<PrescriptionDTO> getAllPrescriptions() {
        try {
            List<Prescription> prescriptions = prescriptionRepository.findAll();
            List<PrescriptionDTO> prescriptionDTOs = new ArrayList<>();
            for (Prescription prescription : prescriptions) {
                PrescriptionDTO prescriptionDTO = new PrescriptionDTO();
                prescriptionDTO.setPrescriptionId(prescription.getPrescriptionId());
                prescriptionDTO.setPrescriptionDate(prescription.getPrescriptionDate());
                prescriptionDTO.setDosage(prescription.getDosage());
                prescriptionDTO.setFrequency(prescription.getFrequency());
                prescriptionDTO.setInstructions(prescription.getInstructions());
                prescriptionDTO.setDurationDays(prescription.getDurationDays());
                prescriptionDTO.setPatientId(prescription.getPatient().getPatientId());
                prescriptionDTO.setDoctorId(prescription.getDoctor().getDoctorId());
                prescriptionDTO.setMedicationId(prescription.getMedication().getMedicationId());

                prescriptionDTOs.add(prescriptionDTO);
            }return prescriptionDTOs;
        }catch (Exception e){
            log.error("getAllPrescriptions error");
            throw e;
        }
    }

    @Override
    public void updatePrescription(PrescriptionDTO prescriptionDTO) {
        try {

            Optional<Prescription> optionalPrescription = prescriptionRepository.findById(prescriptionDTO.getPrescriptionId());
            if (optionalPrescription.isEmpty()) {
                throw new RuntimeException("Prescription Not Found");
            }

            Optional<Patient> optionalPatient = patientRepository.findById(prescriptionDTO.getPatientId());
            if (optionalPatient.isEmpty()) {
                throw new RuntimeException("Patient Not Found");
            }

            Optional<Doctor> optionalDoctor = doctorRepository.findById(prescriptionDTO.getDoctorId());
            if (optionalDoctor.isEmpty()) {
                throw new RuntimeException("Doctor Not Found");
            }

            Optional<Medication> optionalMedication = medicationRepository.findById(prescriptionDTO.getMedicationId());
            if (optionalMedication.isEmpty()) {
                throw new RuntimeException("Medication Not Found");
            }
            Prescription prescription = new Prescription();
            prescription.setPrescriptionDate(prescriptionDTO.getPrescriptionDate());
            prescription.setDosage(prescriptionDTO.getDosage());
            prescription.setFrequency(prescriptionDTO.getFrequency());
            prescription.setInstructions(prescriptionDTO.getInstructions());
            prescription.setDurationDays(prescriptionDTO.getDurationDays());
            prescription.setPatient(optionalPatient.get());
            prescription.setDoctor(optionalDoctor.get());
            prescription.setMedication(optionalMedication.get());
            prescriptionRepository.save(prescription);

        }catch (Exception e){
            log.error("updatePrescription error");
            throw e;
        }
    }

    @Override
    public List<PrescriptionDTO> filterPrescription(Long id) {
        try {
            List<PrescriptionDTO> prescriptionDTOs = new ArrayList<>();
            List<Prescription> prescription = prescriptionRepository.filterPrescription(id);            for (Prescription prescription1 : prescription) {
                PrescriptionDTO prescriptionDTO = new PrescriptionDTO(
                        prescription1.getPrescriptionId(),
                        prescription1.getPrescriptionDate(),
                        prescription1.getDosage(),
                        prescription1.getFrequency(),
                        prescription1.getDurationDays(),
                        prescription1.getInstructions(),
                        prescription1.getPatient().getPatientId(),
                        prescription1.getDoctor().getDoctorId(),
                        prescription1.getMedication().getMedicationId()
                );
                prescriptionDTOs.add(prescriptionDTO);
            }
            return prescriptionDTOs;
        }catch (Exception e){
            log.error("filterPrescription error");
            throw e;
        }
    }
}
