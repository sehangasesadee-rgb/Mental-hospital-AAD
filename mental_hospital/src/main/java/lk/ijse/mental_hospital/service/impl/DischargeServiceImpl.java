package lk.ijse.mental_hospital.service.impl;

import lk.ijse.mental_hospital.constant.CommonResponse;
import lk.ijse.mental_hospital.dto.DischargeDTO;
import lk.ijse.mental_hospital.entity.Admission;
import lk.ijse.mental_hospital.entity.Discharge;
import lk.ijse.mental_hospital.entity.Doctor;
import lk.ijse.mental_hospital.enumaration.DischargeStatus;
import lk.ijse.mental_hospital.repository.AdmissionRepository;
import lk.ijse.mental_hospital.repository.DischargeRepository;
import lk.ijse.mental_hospital.repository.DoctorRepository;
import lk.ijse.mental_hospital.service.DischargeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class DischargeServiceImpl implements DischargeService {
    private final DischargeRepository dischargeRepository;
    private final DoctorRepository doctorRepository;
    private final AdmissionRepository admissionRepository;

    public DischargeServiceImpl(DischargeRepository dischargeRepository, DoctorRepository doctorRepository, AdmissionRepository admissionRepository) {
        this.dischargeRepository = dischargeRepository;
        this.doctorRepository = doctorRepository;
        this.admissionRepository = admissionRepository;
    }

    @Override
    public void saveDischarge(DischargeDTO dischargeDTO) {
        log.info("Discharge service start");

        Optional<Doctor>  optionaldoctor = doctorRepository.findById(dischargeDTO.getDoctorId());
        if(optionaldoctor.isEmpty()){
            throw new RuntimeException("Doctor not found");
        }

        Optional<Admission> optionalAdmission = admissionRepository.findById(dischargeDTO.getAdmissionId());
        if(optionalAdmission.isEmpty()){
            throw new RuntimeException("Admission not found");
        }


        Discharge discharge = new Discharge();
        discharge.setDischargeDate(dischargeDTO.getDischargeDate());
        discharge.setRemarks(dischargeDTO.getRemarks());
        discharge.setDischargeStatus(dischargeDTO.getDischargeStatus());
        discharge.setDoctor(optionaldoctor.get());
        discharge.setAdmission(optionalAdmission.get());
        dischargeRepository.save(discharge);
        log.info("Discharge service end");


    }

    @Override
    public List<DischargeDTO> getAllDischarges() {
        try {
            List<Discharge> discharges = dischargeRepository.findAll();
            List<DischargeDTO> dischargeDTOs = new ArrayList<>();
            for (Discharge discharge : discharges) {
                DischargeDTO dischargeDTO = new DischargeDTO();
                dischargeDTO.setDischargeId(discharge.getDischargeId());
                dischargeDTO.setDischargeDate(discharge.getDischargeDate());
                dischargeDTO.setRemarks(discharge.getRemarks());
                dischargeDTO.setDischargeStatus(discharge.getDischargeStatus());
                dischargeDTO.setDoctorId(discharge.getDoctor().getDoctorId());
                dischargeDTO.setAdmissionId(discharge.getAdmission().getAdmissionId());
                dischargeDTOs.add(dischargeDTO);

            }
            return dischargeDTOs;
        }catch (Exception e){
            log.error("getAllDischarges error");
            throw e;

        }
    }

    @Override
    public void updateDischarge(DischargeDTO dischargeDTO) {
        log.info("Discharge service start");
        try {
            Optional<Discharge> optionalDischarge = dischargeRepository.findById(dischargeDTO.getDischargeId());
            if(optionalDischarge.isEmpty()){
                throw new RuntimeException("Discharge not found");
            }

            Optional<Doctor> optionalDoctor = doctorRepository.findById(dischargeDTO.getDoctorId());
            if(optionalDoctor.isEmpty()){
                throw new RuntimeException("Doctor not found");
            }

            Optional<Admission> optionalAdmission = admissionRepository.findById(dischargeDTO.getAdmissionId());
            if(optionalAdmission.isEmpty()){
                throw new RuntimeException("Admission not found");
            }

            Discharge discharge = optionalDischarge.get();
            discharge.setDischargeDate(dischargeDTO.getDischargeDate());
            discharge.setRemarks(dischargeDTO.getRemarks());
            discharge.setDischargeStatus(dischargeDTO.getDischargeStatus());
            discharge.setDoctor(optionalDoctor.get());
            discharge.setAdmission(optionalAdmission.get());
            dischargeRepository.save(discharge);
        } catch (Exception e) {
            log.info("Update Discharge error");

        }
    }

    @Override
    public List<DischargeDTO> filterDischarge(Long id) {
        try {
            List<Discharge> optionalDischarge = dischargeRepository.filterDischarge(id);
            List<DischargeDTO> dischargeDTOs = new ArrayList<>();
            for (Discharge discharge : optionalDischarge) {
                DischargeDTO dischargeDTO = new DischargeDTO(
                        discharge.getDischargeId(),
                        discharge.getDischargeDate(),
                        discharge.getRemarks(),
                        discharge.getDischargeStatus(),
                        discharge.getAdmission().getAdmissionId(),
                        discharge.getDoctor().getDoctorId()
                );
                dischargeDTOs.add(dischargeDTO);

            }
            return dischargeDTOs;


        } catch (Exception e) {
            log.info("filterDischarge error");
            throw e;

        }
    }

    @Override
    public void changeDischarge(long id) {
        log.info("Discharge service start");
        try {
            Optional<Discharge> optionalDischarge = dischargeRepository.findById(id);

            if(optionalDischarge.isEmpty()){
                throw new RuntimeException("Discharge not found");
            }
            Discharge discharge = optionalDischarge.get();
            discharge.setDischargeStatus(DischargeStatus.DISCHARGED);
            dischargeRepository.save(discharge);
        } catch (Exception e) {
            log.info("Change Discharge error");
            throw e;
        }
    }
}
