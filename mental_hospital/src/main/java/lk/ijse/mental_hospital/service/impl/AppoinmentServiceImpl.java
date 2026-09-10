package lk.ijse.mental_hospital.service.impl;

import lk.ijse.mental_hospital.dto.AppoinmentDTO;
import lk.ijse.mental_hospital.entity.Appointment;
import lk.ijse.mental_hospital.entity.Doctor;
import lk.ijse.mental_hospital.entity.Patient;
import lk.ijse.mental_hospital.enumaration.AppoinmentStatus;
import lk.ijse.mental_hospital.repository.AppoinmentRepository;
import lk.ijse.mental_hospital.repository.DoctorRepository;
import lk.ijse.mental_hospital.repository.PatientRepository;
import lk.ijse.mental_hospital.service.AppoinmentService;
import lk.ijse.mental_hospital.service.DoctorService;
import lk.ijse.mental_hospital.service.PatientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class AppoinmentServiceImpl implements AppoinmentService {
//    private AppoinmentService appoinmentService;
//    private PatientService patientService;
//    private DoctorService doctorService;
//    public AppoinmentServiceImpl(AppoinmentService appoinmentService, PatientService patientService, DoctorService doctorService) {
//        this.appoinmentService = appoinmentService;
//        this.patientService = patientService;
//        this.doctorService = doctorService;
//    }

    private final AppoinmentRepository appoinmentRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;


    @Override
    public void saveAppoinment(AppoinmentDTO appoinmentDTO) {
        log.info("saveAppoinment");

        Optional<Doctor> optionalDoctor = doctorRepository.findById(appoinmentDTO.getDoctorId());
        if (optionalDoctor.isEmpty()) {
            throw new RuntimeException("Doctor not found");
        }

        Optional<Patient> patientOptional = patientRepository.findById(appoinmentDTO.getPatientId());
        if ((patientOptional.isEmpty())){
            throw new RuntimeException("Patient not found");
        }



        Appointment appointment = new Appointment();
        appointment.setAppointmentDate(appoinmentDTO.getAppointmentDate());
        appointment.setAppointmentTime(appoinmentDTO.getAppointmentTime());
        appointment.setAppointmentStatus(appoinmentDTO.getAppointmentStatus());
        appointment.setReason(appoinmentDTO.getReason());
        appointment.setPatient(patientOptional.get());
        appointment.setDoctor(optionalDoctor.get());
        appoinmentRepository.save(appointment);
        log.info("save appoinment");



    }

    @Override
    public List<AppoinmentDTO> getAppoinments() {
        try {
            List<AppoinmentDTO> appoinmentDTOList = new ArrayList<>();
            List<Appointment> appoinmentList = appoinmentRepository.findAll();
            for (Appointment appointment : appoinmentList) {
                AppoinmentDTO appoinmentDTO = new AppoinmentDTO();
                appoinmentDTO.setAppointmentId(appointment.getAppointmentId());
                appoinmentDTO.setAppointmentDate(appointment.getAppointmentDate());
                appoinmentDTO.setAppointmentTime(appointment.getAppointmentTime());
                appoinmentDTO.setAppointmentStatus(appointment.getAppointmentStatus());
                appoinmentDTO.setReason(appointment.getReason());
                appoinmentDTO.setPatientId(appointment.getPatient().getPatientId());
                appoinmentDTO.setDoctorId(appointment.getDoctor().getDoctorId());
                appoinmentDTOList.add(appoinmentDTO);
            }return appoinmentDTOList;

        }catch (Exception e){
            log.info("save appoinment");
            throw e;
        }
    }

    @Override
    public void updateAppoinment(AppoinmentDTO appoinmentDTO) {
        log.info("updateAppoinment");
        try {
            Optional<Appointment> optionalAppointment = appoinmentRepository.findById(appoinmentDTO.getAppointmentId());
            if (optionalAppointment.isEmpty()) {
                throw new RuntimeException("Appointment not found");
            }

            Optional<Patient> optionalPatient = patientRepository.findById(appoinmentDTO.getPatientId());
            if (optionalPatient.isEmpty()) {
                throw new RuntimeException("Patient not found");
            }
            Optional<Doctor> optionalDoctor = doctorRepository.findById(appoinmentDTO.getDoctorId());
            if (optionalDoctor.isEmpty()) {
                throw new RuntimeException("Doctor not found");
            }
            Appointment appointment = optionalAppointment.get();
            appointment.setAppointmentDate(appoinmentDTO.getAppointmentDate());
            appointment.setAppointmentTime(appoinmentDTO.getAppointmentTime());
            appointment.setReason(appoinmentDTO.getReason());
            appointment.setAppointmentStatus(appoinmentDTO.getAppointmentStatus());
            appointment.setPatient(optionalPatient.get());
            appointment.setDoctor(optionalDoctor.get());
            appoinmentRepository.save(appointment);
        }catch (Exception e){
            log.info("updateAppoinment");
        }
    }

    @Override
    public List<AppoinmentDTO> filterAppoinments(Long appoinment_id,Long patient_id) {
        try {
            List<AppoinmentDTO> appoinmentDTOList = new ArrayList<>();
            List<Appointment> appointments = appoinmentRepository.filterAppoinments(appoinment_id,patient_id);
            for (Appointment appointment : appointments) {
                AppoinmentDTO appoinmentDTO = new AppoinmentDTO(
                        appointment.getAppointmentId(),
                        appointment.getAppointmentDate(),
                        appointment.getAppointmentTime(),
                        appointment.getAppointmentStatus(),
                        appointment.getReason(),

                        appointment.getPatient().getPatientId(),
                        appointment.getDoctor().getDoctorId()
                );
                appoinmentDTOList.add(appoinmentDTO);
            }
            return appoinmentDTOList;
        }catch (Exception e){
            log.info("filterAppoinments");
            throw e;
        }
    }






    /// /meka hdn thiye
    @Override
    public void changeAppoinmentStatus(long id) {
        log.info("changeAppoinmentAppoinment");
        try {
            Optional<Appointment> optionalAppointment = appoinmentRepository.findById(id);
            if (optionalAppointment.isEmpty()) {
                throw new RuntimeException("Appointment not found");
            }
            Optional<Patient> optionalPatient = patientRepository.findById(optionalAppointment.get().getPatient().getPatientId());
            if (optionalPatient.isEmpty()) {
                throw new RuntimeException("Patient not found");
            }
            Optional<Doctor> optionalDoctor = doctorRepository.findById(optionalAppointment.get().getDoctor().getDoctorId());
            if (optionalDoctor.isEmpty()) {
                throw new RuntimeException("Doctor not found");
            }
            Appointment appointment = optionalAppointment.get();
            appointment.setAppointmentStatus(AppoinmentStatus.CANCELLED);
            appoinmentRepository.save(appointment);
        }catch (Exception e){
            log.info("changeAppoinmentAppoinment");
        }
    }
}
