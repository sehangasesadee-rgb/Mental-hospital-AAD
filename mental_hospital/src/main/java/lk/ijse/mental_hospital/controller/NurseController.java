package lk.ijse.mental_hospital.controller;


import lk.ijse.mental_hospital.constant.CommonResponse;
import lk.ijse.mental_hospital.dto.NurseDTO;
import lk.ijse.mental_hospital.entity.Nurse;
import lk.ijse.mental_hospital.service.DepartmentService;
import lk.ijse.mental_hospital.service.NurseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static lk.ijse.mental_hospital.constant.ResponsCode.OPERATION_SUCCESS;
import static lk.ijse.mental_hospital.constant.ResponseMassage.SUCCESS_MASSAGE;

@RestController
@RequestMapping("v1/nurses")
@Slf4j
public class NurseController {
    private final NurseService nurseService;
    private final DepartmentService departmentService;
    public NurseController(NurseService nurseService, DepartmentService departmentService) {
        this.nurseService = nurseService;
        this.departmentService = departmentService;

    }
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse saveNurse(@RequestBody NurseDTO nurseDTO){
//        NurseService nurseService1(nurseDTO);
        nurseService.saveNurse(nurseDTO);
        return new CommonResponse(OPERATION_SUCCESS, SUCCESS_MASSAGE);
    }
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse getAllNurses(){
        return new CommonResponse(OPERATION_SUCCESS, nurseService.findAllNurse(),SUCCESS_MASSAGE);
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse updateNurse(@RequestBody NurseDTO nurseDTO){
        nurseService.updateNurse(nurseDTO);
        return new CommonResponse(OPERATION_SUCCESS, SUCCESS_MASSAGE);
    }

    @GetMapping(value = "/filter",produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse getAllNursesByFilter(@RequestParam(value = "nurseName",required = false)String nurseName ){
        List<NurseDTO> nurseDTOList = nurseService.filterNurse(nurseName);
        return new CommonResponse(OPERATION_SUCCESS, nurseDTOList,SUCCESS_MASSAGE);
    }
}
