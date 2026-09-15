package lk.ijse.mental_hospital.controller;

import lk.ijse.mental_hospital.constant.CommonResponse;
import lk.ijse.mental_hospital.dto.TreatmentDTO;
import lk.ijse.mental_hospital.entity.Treatment;
import lk.ijse.mental_hospital.service.DoctorService;
import lk.ijse.mental_hospital.service.PatientService;
import lk.ijse.mental_hospital.service.TreatmentService;
import lk.ijse.mental_hospital.service.impl.DoctorServiceImpl;
import lk.ijse.mental_hospital.service.impl.PatientServiceImpl;
import lk.ijse.mental_hospital.service.impl.TreatmentServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static lk.ijse.mental_hospital.constant.ResponsCode.OPERATION_SUCCESS;
import static lk.ijse.mental_hospital.constant.ResponseMassage.SUCCESS_MASSAGE;


@Slf4j
@RestController
@RequestMapping("v1/treatment")
public class TreatmentController {
    private TreatmentService treatmentService;
    private PatientService patientService;
    private DoctorService doctorService;

    public TreatmentController(TreatmentService treatmentService, PatientService patientService, DoctorService doctorService) {
        this.treatmentService = treatmentService;
        this.patientService = patientService;
        this.doctorService = doctorService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse createTreatment(@RequestBody TreatmentDTO treatmentDTO) {
        treatmentService.addTreatment(treatmentDTO);
        return new CommonResponse(OPERATION_SUCCESS,SUCCESS_MASSAGE);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse getAllTreatment() {
        return new CommonResponse(OPERATION_SUCCESS,treatmentService.findAllTreatment(),SUCCESS_MASSAGE);
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse updateTreatment(@RequestBody TreatmentDTO treatmentDTO) {
        treatmentService.updateTreatment(treatmentDTO);
        return new CommonResponse(OPERATION_SUCCESS,SUCCESS_MASSAGE);
    }

//    @GetMapping(value = "/filter",produces = MediaType.APPLICATION_JSON_VALUE)
//    public CommonResponse filterAdmission(@RequestParam(value = "treatment_id", required = false) Long treatment_id){
//        List<TreatmentDTO> treatments = treatmentService.filterTreatments(treatment_id);
//        return  new CommonResponse(OPERATION_SUCCESS,treatments,SUCCESS_MASSAGE);
//
//    }

    @GetMapping(value = "/filter", produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse filterTreatment(@RequestParam(value = "id", required = false) Long id) {
        List<TreatmentDTO> treatmentDTOs = treatmentService.filterTreatments(id);

        return new CommonResponse(
                OPERATION_SUCCESS,
                treatmentDTOs,
                SUCCESS_MASSAGE
        );
    }

    @DeleteMapping("/{id}")
    public CommonResponse deleteTreatment(@PathVariable Long id) {
        treatmentService.changeStatus(id);
        return new CommonResponse(OPERATION_SUCCESS,SUCCESS_MASSAGE);
    }
}
