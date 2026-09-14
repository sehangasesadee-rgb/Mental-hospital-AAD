package lk.ijse.mental_hospital.controller;


import lk.ijse.mental_hospital.constant.CommonResponse;
import lk.ijse.mental_hospital.dto.DiagnosisDTO;
import lk.ijse.mental_hospital.repository.DiagnosisRepository;
import lk.ijse.mental_hospital.repository.PatientRepository;
import lk.ijse.mental_hospital.service.DiagnosisService;
import lk.ijse.mental_hospital.service.DoctorService;
import lk.ijse.mental_hospital.service.PatientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static lk.ijse.mental_hospital.constant.ResponsCode.OPERATION_SUCCESS;
import static lk.ijse.mental_hospital.constant.ResponseMassage.SUCCESS_MASSAGE;

@RestController
@RequestMapping("v1/diagnosis")
@Slf4j
public class DiagnosisController {
    private final DiagnosisService diagnosisService;
    private  final PatientService patientService;
    private final DoctorService doctorService;

    public DiagnosisController(DiagnosisService diagnosisService, PatientService patientService, DoctorService doctorService) {
        this.diagnosisService = diagnosisService;
        this.patientService = patientService;
        this.doctorService = doctorService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse saveDiagnosis(@RequestBody DiagnosisDTO diagnosisDTO) {
        diagnosisService.saveDiagnosis(diagnosisDTO);
        return new CommonResponse(OPERATION_SUCCESS, SUCCESS_MASSAGE);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse getAllDiagnosis() {
        return new CommonResponse(OPERATION_SUCCESS,diagnosisService.getGetAllDiagnosis() ,SUCCESS_MASSAGE);
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse updateDiagnosis(@RequestBody DiagnosisDTO diagnosisDTO) {
        diagnosisService.updateDiagnosis(diagnosisDTO);
        return new CommonResponse(OPERATION_SUCCESS,SUCCESS_MASSAGE);

    }

    @GetMapping(value = "/filter",produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse filterDiagnosis(@RequestParam(value = "patientId",required = false) Long patientId) {
        List<DiagnosisDTO> diagnosisDTOS = diagnosisService.filterDiagnosis(patientId);
        return new CommonResponse(OPERATION_SUCCESS,diagnosisDTOS,SUCCESS_MASSAGE);

    }


    //delete ek kre ne


}
