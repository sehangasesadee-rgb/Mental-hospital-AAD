package lk.ijse.mental_hospital.controller;

import lk.ijse.mental_hospital.constant.CommonResponse;
import lk.ijse.mental_hospital.dto.DiagnosisDTO;
import lk.ijse.mental_hospital.dto.DischargeDTO;
import lk.ijse.mental_hospital.service.AdmissionService;
import lk.ijse.mental_hospital.service.DischargeService;
import lk.ijse.mental_hospital.service.DoctorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static lk.ijse.mental_hospital.constant.ResponsCode.OPERATION_SUCCESS;
import static lk.ijse.mental_hospital.constant.ResponseMassage.SUCCESS_MASSAGE;


@RestController
@RequestMapping("v1/discharge")
@Slf4j
@RequiredArgsConstructor
public class DischargeController {
    private final DischargeService dischargeService;
    private final DoctorService doctorService;
    private final AdmissionService admissionService;


    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse saveDischarge(@RequestBody DischargeDTO dischargeDTO) {
        dischargeService.saveDischarge(dischargeDTO);
        return new CommonResponse(OPERATION_SUCCESS,SUCCESS_MASSAGE);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse getAllDischarges(){
        return new CommonResponse(OPERATION_SUCCESS,dischargeService.getAllDischarges(),SUCCESS_MASSAGE);
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse updateDischarge(@RequestBody DischargeDTO dischargeDTO) {
        dischargeService.updateDischarge(dischargeDTO);
        return new CommonResponse(OPERATION_SUCCESS,dischargeDTO,SUCCESS_MASSAGE);
    }

    @GetMapping(value = "/filter",produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse filterDischarge(@RequestParam(value = "dischargeId",required = false) Long dischargeId) {
        List<DischargeDTO> filterDischarge = dischargeService.filterDischarge(dischargeId);
        return new CommonResponse(OPERATION_SUCCESS,filterDischarge,SUCCESS_MASSAGE);
    }

    @DeleteMapping(value = "/{dischargedId}" ,produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse deleteDischarge(@PathVariable Long dischargedId) {
        dischargeService.changeDischarge(dischargedId);
        return new CommonResponse(OPERATION_SUCCESS,SUCCESS_MASSAGE);
    }
}

