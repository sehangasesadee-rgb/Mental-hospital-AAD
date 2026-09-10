package lk.ijse.mental_hospital.controller;


import lk.ijse.mental_hospital.constant.CommonResponse;
import lk.ijse.mental_hospital.dto.AdmissionDTO;
import lk.ijse.mental_hospital.entity.Admission;
import lk.ijse.mental_hospital.service.AdmissionService;
import lk.ijse.mental_hospital.service.BedService;
import lk.ijse.mental_hospital.service.DoctorService;
import lk.ijse.mental_hospital.service.PatientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import static lk.ijse.mental_hospital.constant.ResponsCode.OPERATION_SUCCESS;
import static lk.ijse.mental_hospital.constant.ResponseMassage.SUCCESS_MASSAGE;

@RestController
@Slf4j
@RequestMapping("v1/admission")
public class AdmissionController {
    private final AdmissionService admissionService;
    private final PatientService patientService;
    private final DoctorService doctorService;
    private final BedService bedService;

    public AdmissionController(AdmissionService admissionService, PatientService patientService, DoctorService doctorService, BedService bedService) {
        this.admissionService = admissionService;
        this.patientService = patientService;
        this.doctorService = doctorService;
        this.bedService = bedService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse saveAdmission(@RequestBody AdmissionDTO admissionDTO) {
        admissionService.saveAdmission(admissionDTO);
        return new CommonResponse(OPERATION_SUCCESS, SUCCESS_MASSAGE);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse findAllAdmissions() {
        return new CommonResponse(OPERATION_SUCCESS, admissionService.findAllAdmissions(),SUCCESS_MASSAGE);
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse updateAdmission(@RequestBody AdmissionDTO admissionDTO) {
        admissionService.updateAdmission(admissionDTO);
        return new CommonResponse(OPERATION_SUCCESS, SUCCESS_MASSAGE);
    }

}
