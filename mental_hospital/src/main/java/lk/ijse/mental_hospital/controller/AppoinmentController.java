package lk.ijse.mental_hospital.controller;

import jakarta.validation.constraints.Max;
import lk.ijse.mental_hospital.constant.CommonResponse;
import lk.ijse.mental_hospital.dto.AppoinmentDTO;
import lk.ijse.mental_hospital.service.AppoinmentService;
import lk.ijse.mental_hospital.service.DoctorService;
import lk.ijse.mental_hospital.service.PatientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static lk.ijse.mental_hospital.constant.ResponsCode.OPERATION_SUCCESS;
import static lk.ijse.mental_hospital.constant.ResponseMassage.SUCCESS_MASSAGE;

@RestController
@RequestMapping("v1/appoinments")
@Slf4j
public class AppoinmentController {
    private final AppoinmentService appoinmentService;
    private final DoctorService doctorService;
    private final PatientService patientService;
    public  AppoinmentController(AppoinmentService appoinmentService, DoctorService doctorService, PatientService patientService) {
        this.appoinmentService = appoinmentService;
        this.doctorService = doctorService;
        this.patientService = patientService;
    }
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse saveAppoinment(@RequestBody AppoinmentDTO appoinmentDTO) {
        appoinmentService.saveAppoinment(appoinmentDTO);
        return new CommonResponse(OPERATION_SUCCESS, SUCCESS_MASSAGE);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse getAppoinments() {
        return new CommonResponse(OPERATION_SUCCESS,appoinmentService.getAppoinments() ,SUCCESS_MASSAGE);
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse updateAppoinment(@RequestBody AppoinmentDTO appoinmentDTO) {
        appoinmentService.updateAppoinment(appoinmentDTO);
        return new CommonResponse(OPERATION_SUCCESS,SUCCESS_MASSAGE);
    }

    @GetMapping(value = "/filter", produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse filterAppoinments(
            @RequestParam(value = "appointmentId", required = false) Long appointmentId,
            @RequestParam(value = "patientId", required = false) Long patientId) {

        List<AppoinmentDTO> appoinmentDTOS =
                appoinmentService.filterAppoinments(appointmentId, patientId);

        return new CommonResponse(
                OPERATION_SUCCESS,
                appoinmentDTOS,
                SUCCESS_MASSAGE
        );
    }

    @DeleteMapping(value = "/{appoinmentId}",produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse deleteAppoinmentStatus(@PathVariable Long appoinmentId) {
        appoinmentService.changeAppoinmentStatus(appoinmentId);
        return new CommonResponse(OPERATION_SUCCESS,SUCCESS_MASSAGE);
    }

}
