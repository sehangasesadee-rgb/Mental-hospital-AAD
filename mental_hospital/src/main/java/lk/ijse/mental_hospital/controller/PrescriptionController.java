package lk.ijse.mental_hospital.controller;

import lk.ijse.mental_hospital.constant.CommonResponse;
import lk.ijse.mental_hospital.dto.PrescriptionDTO;
import lk.ijse.mental_hospital.repository.MedicationRepository;
import lk.ijse.mental_hospital.repository.PrescriptionRepository;
import lk.ijse.mental_hospital.service.MedicationService;
import lk.ijse.mental_hospital.service.PatientService;
import lk.ijse.mental_hospital.service.PrescriptionService;
import lk.ijse.mental_hospital.service.impl.DoctorServiceImpl;
import lk.ijse.mental_hospital.service.impl.MedicationServiceImpl;
import lk.ijse.mental_hospital.service.impl.PatientServiceImpl;
import lk.ijse.mental_hospital.service.impl.PrescriptionServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static lk.ijse.mental_hospital.constant.ResponsCode.OPERATION_SUCCESS;
import static lk.ijse.mental_hospital.constant.ResponseMassage.SUCCESS_MASSAGE;


@RestController
@RequestMapping("v1/prescription")
@Slf4j
public class PrescriptionController {
    private final PrescriptionService prescriptionService;
    private final MedicationService medicationService;
    private final PatientServiceImpl patientService;
    private final DoctorServiceImpl doctorService;

    public PrescriptionController(PrescriptionService prescriptionService, MedicationService medicationService, PatientServiceImpl patientService, DoctorServiceImpl doctorService) {
        this.prescriptionService = prescriptionService;
        this.medicationService = medicationService;
        this.patientService = patientService;
        this.doctorService = doctorService;

    }
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse addPrescription(@RequestBody PrescriptionDTO prescriptionDTO) {
        prescriptionService.addPrescription(prescriptionDTO);
        return new CommonResponse(OPERATION_SUCCESS, SUCCESS_MASSAGE);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse getAllPrescriptions() {
        return new CommonResponse(OPERATION_SUCCESS,prescriptionService.getAllPrescriptions() ,SUCCESS_MASSAGE);

    }
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse updatePrescription(@RequestBody PrescriptionDTO prescriptionDTO) {
        prescriptionService.updatePrescription(prescriptionDTO);
        return new CommonResponse(OPERATION_SUCCESS, SUCCESS_MASSAGE);
    }

    @GetMapping(value = "/filter",produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse filterPrescription(@RequestParam(value = "prescriptionId", required = false) Long prescriptionId) {
        List<PrescriptionDTO> prescriptionDTOList = prescriptionService.filterPrescription(prescriptionId);
        return new CommonResponse(OPERATION_SUCCESS,prescriptionDTOList,SUCCESS_MASSAGE);

    }

}
