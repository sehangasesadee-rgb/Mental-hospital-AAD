package lk.ijse.mental_hospital.controller;


import lk.ijse.mental_hospital.constant.CommonResponse;
import lk.ijse.mental_hospital.dto.DoctorDTO;
import lk.ijse.mental_hospital.entity.Doctor;
import lk.ijse.mental_hospital.enumaration.DoctorSpecialization;
import lk.ijse.mental_hospital.service.DoctorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static lk.ijse.mental_hospital.constant.ResponsCode.OPERATION_SUCCESS;
import static lk.ijse.mental_hospital.constant.ResponseMassage.SUCCESS_MASSAGE;

@RestController
@RequestMapping("v1/doctors")
@Slf4j
public class DoctorController {
    private final DoctorService doctorService;
    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse saveDoctor(@RequestBody DoctorDTO doctorDTO){
        doctorService.saveDoctor(doctorDTO);
        return new CommonResponse(OPERATION_SUCCESS,SUCCESS_MASSAGE);

    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse getAllDoctors(){
        return new CommonResponse(OPERATION_SUCCESS, doctorService.getAllDoctor(), SUCCESS_MASSAGE);
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse updateDoctor(@RequestBody DoctorDTO doctorDTO){
        doctorService.updateDoctor(doctorDTO);
        return new CommonResponse(OPERATION_SUCCESS,SUCCESS_MASSAGE);
    }

    @DeleteMapping(value = "/{doctorId}",produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse deleteDoctor(@PathVariable Long doctorId){
        doctorService.ChangeDoctorStatus(doctorId);
        return new CommonResponse(OPERATION_SUCCESS,SUCCESS_MASSAGE);
    }

    @GetMapping(value = "/filter",produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse filterDoctor(@RequestParam(value = "doctorName",required = false) String doctorName){
        List<DoctorDTO> doctorDTOList = doctorService.filterDoctor(doctorName );
        return new CommonResponse(OPERATION_SUCCESS,doctorDTOList,SUCCESS_MASSAGE);
    }



}
