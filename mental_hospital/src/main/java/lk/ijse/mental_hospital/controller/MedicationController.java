package lk.ijse.mental_hospital.controller;

import lk.ijse.mental_hospital.constant.CommonResponse;
import lk.ijse.mental_hospital.dto.MedicationDTO;
import lk.ijse.mental_hospital.entity.Medication;
import lk.ijse.mental_hospital.service.MedicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static lk.ijse.mental_hospital.constant.ResponsCode.OPERATION_SUCCESS;
import static lk.ijse.mental_hospital.constant.ResponseMassage.SUCCESS_MASSAGE;


@RestController
@RequestMapping("v1/medication")
@Slf4j
public class MedicationController {
    private MedicationService medicationService;
    public MedicationController(MedicationService medicationService) {
        this.medicationService = medicationService;
    }
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse addMedication(@RequestBody MedicationDTO medicationDTO) {
        medicationService.addMedication(medicationDTO);
        return new CommonResponse(OPERATION_SUCCESS,SUCCESS_MASSAGE);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse getMedications(){
        return new CommonResponse(OPERATION_SUCCESS,medicationService.getMedications(),SUCCESS_MASSAGE);
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse updateMedication(@RequestBody MedicationDTO medicationDTO) {
        medicationService.updateMedication(medicationDTO);
        return new CommonResponse(OPERATION_SUCCESS,SUCCESS_MASSAGE);
    }

    @GetMapping(value = "/filter",produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse filterMedication(@RequestParam(value = "Medication_id",required = false) Long id){
        List<MedicationDTO> medications = medicationService.filterMedication(id);
        return new CommonResponse(OPERATION_SUCCESS,medications,SUCCESS_MASSAGE);
    }
}
