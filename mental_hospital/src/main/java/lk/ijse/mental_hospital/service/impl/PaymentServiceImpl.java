package lk.ijse.mental_hospital.service.impl;

import lk.ijse.mental_hospital.dto.PaymentDTO;
import lk.ijse.mental_hospital.entity.Admission;
import lk.ijse.mental_hospital.entity.Appointment;
import lk.ijse.mental_hospital.entity.Patient;
import lk.ijse.mental_hospital.entity.Payment;
import lk.ijse.mental_hospital.enumaration.PaymentStatus;
import lk.ijse.mental_hospital.repository.AdmissionRepository;
import lk.ijse.mental_hospital.repository.AppoinmentRepository;
import lk.ijse.mental_hospital.repository.PatientRepository;
import lk.ijse.mental_hospital.repository.PaymentRepository;
import lk.ijse.mental_hospital.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final PatientRepository patientRepository;
    private final AdmissionRepository admissionRepository;
    private final AppoinmentRepository appoinmentRepository;

    public PaymentServiceImpl(PaymentRepository paymentRepository, PatientRepository patientRepository, AdmissionRepository admissionRepository, AppoinmentRepository appoinmentRepository) {
        this.paymentRepository = paymentRepository;
        this.patientRepository = patientRepository;
        this.admissionRepository = admissionRepository;
        this.appoinmentRepository = appoinmentRepository;
    }

    @Override
    public void savePayment(PaymentDTO paymentDTO) {
        log.info("savePayment");

        Optional<Patient> optionalPatient = patientRepository.findById(paymentDTO.getPatientId());

        if (optionalPatient.isEmpty()) {
            throw new RuntimeException("Patient Not Found");
        }

        Optional<Admission> optionalAdmission = admissionRepository.findById(paymentDTO.getAdmissionId());

        if (optionalAdmission.isEmpty()) {
            throw new RuntimeException("Admission Not Found");
        }

        Optional<Appointment> appointmentOptional = appoinmentRepository.findById(paymentDTO.getAppointmentId());

        if (appointmentOptional.isEmpty()) {
            throw new RuntimeException("Appointment Not Found");
        }

        Payment payment = new Payment();
        payment.setAmount(paymentDTO.getAmount());
        payment.setPaymentDate(paymentDTO.getPaymentDate());
        payment.setPaymentMethod(paymentDTO.getPaymentMethod());
        payment.setPaymentStatus(paymentDTO.getPaymentStatus());
        payment.setPatient(optionalPatient.get());
        payment.setAdmission(optionalAdmission.get());
        payment.setAppointment(appointmentOptional.get());

        paymentRepository.save(payment);
    }

    @Override
    public List<PaymentDTO> getAllPayments() {
        try {
            List<Payment> payments = paymentRepository.findAll();
            List<PaymentDTO> paymentDTOs = new ArrayList<>();
            for (Payment payment : payments) {
                PaymentDTO paymentDTO = new PaymentDTO();
                paymentDTO.setPaymentId(payment.getPaymentId());
                paymentDTO.setAmount(payment.getAmount());
                paymentDTO.setPaymentDate(payment.getPaymentDate());
                paymentDTO.setPaymentMethod(payment.getPaymentMethod());
                paymentDTO.setPaymentStatus(payment.getPaymentStatus());

                paymentDTO.setPaymentId(payment.getPaymentId());
                paymentDTO.setPatientId(payment.getPatient().getPatientId());
                paymentDTO.setAdmissionId(payment.getAdmission().getAdmissionId());
                paymentDTO.setAppointmentId(payment.getAppointment().getAppointmentId());



                paymentDTOs.add(paymentDTO);
            }return paymentDTOs;
        }catch (Exception e){
            log.info("getAllPayments");
            throw e;
        }
    }

    @Override
    public void updatePayment(PaymentDTO paymentDTO) {
        try {
            Optional<Payment> optionalPayment = paymentRepository.findById(paymentDTO.getPaymentId());
            if (optionalPayment.isEmpty()) {
                throw new RuntimeException("Payment Not Found");
            }
            Optional<Patient> optionalPatient = patientRepository.findById(paymentDTO.getPatientId());
            if (optionalPatient.isEmpty()) {
                throw new RuntimeException("Patient Not Found");
            }
            Optional<Admission> optionalAdmission = admissionRepository.findById(paymentDTO.getAdmissionId());
            if (optionalAdmission.isEmpty()) {
                throw new RuntimeException("Admission Not Found");
            }
            Optional<Appointment> optionalAppointment = appoinmentRepository.findById(paymentDTO.getAppointmentId());
            if (optionalAppointment.isEmpty()) {
                throw new RuntimeException("Appointment Not Found");
            }
            Payment payment = new Payment();
            payment.setPaymentId(paymentDTO.getPaymentId());
            payment.setAmount(paymentDTO.getAmount());
            payment.setPaymentDate(paymentDTO.getPaymentDate());
            payment.setPaymentMethod(paymentDTO.getPaymentMethod());
            payment.setPaymentStatus(paymentDTO.getPaymentStatus());
            payment.setPatient(optionalPatient.get());
            payment.setAdmission(optionalAdmission.get());
            payment.setAppointment(optionalAppointment.get());
            paymentRepository.save(payment);
        }catch (Exception e){
            log.info("updatePayment");
            throw e;
        }
    }

    @Override
    public List<PaymentDTO> filerPayment(Long id) {
        try {
            List<PaymentDTO> paymentDTOs = new ArrayList<>();
            List<Payment> payments = paymentRepository.filterPayment(id);
            for (Payment payment : payments) {
                PaymentDTO paymentDTO = new PaymentDTO();
                paymentDTO.setPaymentId(payment.getPaymentId());
                paymentDTO.setAmount(payment.getAmount());
                paymentDTO.setPaymentDate(payment.getPaymentDate());
                paymentDTO.setPaymentMethod(payment.getPaymentMethod());
                paymentDTO.setPaymentStatus(payment.getPaymentStatus());
                paymentDTO.setPatientId(payment.getPatient().getPatientId());
                paymentDTO.setAdmissionId(payment.getAdmission().getAdmissionId());
                paymentDTO.setAppointmentId(payment.getAppointment().getAppointmentId());
                paymentDTOs.add(paymentDTO);
            }

            return paymentDTOs;

//            Optional<Patient> optionalPatient = patientRepository.findById(id);
//            if (optionalPatient.isEmpty()) {
//                throw new RuntimeException("Patient Not Found");
//
//            }
        } catch (Exception e) {
            log.info("filerPayment");
            throw e;
        }
    }

    @Override
    public void deletePayment(Long id) {
        log.info("deletePayment");
        try {
            Optional<Payment> optionalPayment = paymentRepository.findById(id);
            if (optionalPayment.isEmpty()) {
                throw new RuntimeException("Payment Not Found");
            }
            Optional<Patient> optionalPatient = patientRepository.findById(optionalPayment.get().getPatient().getPatientId());
            if (optionalPatient.isEmpty()) {
                throw new RuntimeException("Patient Not Found");
            }
            Optional<Admission> optionalAdmission = admissionRepository.findById(optionalPayment.get().getAdmission().getAdmissionId());
            if (optionalAdmission.isEmpty()) {
                throw new RuntimeException("Admission Not Found");
            }
            Optional<Appointment> optionalAppointment = appoinmentRepository.findById(optionalPayment.get().getAppointment().getAppointmentId());
            if (optionalAppointment.isEmpty()) {
                throw new RuntimeException("Appointment Not Found");
            }
            Payment payment = optionalPayment.get();
            payment.setPaymentStatus(PaymentStatus.CANCELLED);
            paymentRepository.save(payment);

        } catch (Exception e) {
                log.info("deletePayment");
        }
    }
}