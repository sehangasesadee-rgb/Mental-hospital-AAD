package lk.ijse.mental_hospital.controller;


import lk.ijse.mental_hospital.constant.CommonResponse;
import lk.ijse.mental_hospital.dto.PatientDTO;
import lk.ijse.mental_hospital.entity.Patient;
import lk.ijse.mental_hospital.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static lk.ijse.mental_hospital.constant.ResponsCode.OPERATION_SUCCESS;
import static lk.ijse.mental_hospital.constant.ResponseMassage.SUCCESS_MASSAGE;

@RestController
@RequestMapping("v1/patients")
public class PatientController {
    private final PatientService patientService;
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse savePatient(@RequestBody PatientDTO patientDTO) {
        patientService.savePatient(patientDTO);
        return new CommonResponse(OPERATION_SUCCESS,SUCCESS_MASSAGE);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse getPatients() {
        return new CommonResponse(OPERATION_SUCCESS,patientService.getPatients(),SUCCESS_MASSAGE);
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse updatePatient(@RequestBody PatientDTO patientDTO) {
        patientService.updatePatient(patientDTO);
        return new CommonResponse(OPERATION_SUCCESS,SUCCESS_MASSAGE);
    }

    @DeleteMapping(value = "/{patientId}",produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse changePatientStatus(@PathVariable Long patientId) {
        patientService.changePatientStatus(patientId);
        return new CommonResponse(OPERATION_SUCCESS,SUCCESS_MASSAGE);
    }
    @GetMapping(value = "/filter",produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse filterPatientsByName(@RequestParam("name") String name) {
        List<PatientDTO> patients = patientService.filterPatientsByName(name);
        return new CommonResponse(OPERATION_SUCCESS,patients,SUCCESS_MASSAGE);
    }
}
