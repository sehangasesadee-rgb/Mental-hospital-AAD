package lk.ijse.mental_hospital.controller;


import lk.ijse.mental_hospital.constant.CommonResponse;
import lk.ijse.mental_hospital.dto.WardDTO;
import lk.ijse.mental_hospital.service.WardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static lk.ijse.mental_hospital.constant.ResponsCode.OPERATION_SUCCESS;
import static lk.ijse.mental_hospital.constant.ResponseMassage.SUCCESS_MASSAGE;

@RestController
@RequestMapping("v1/wards")
@Slf4j
public class WardController {
    private final WardService wardService;
    public WardController(WardService wardService) {
        this.wardService = wardService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse saveWard(@RequestBody WardDTO wardDTO) {
        wardService.saveWard(wardDTO);
        return new CommonResponse(OPERATION_SUCCESS, SUCCESS_MASSAGE);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse getAllWards(){
        List<WardDTO> wardDTOS = wardService.getAllWards();
        return new CommonResponse(OPERATION_SUCCESS, wardDTOS,SUCCESS_MASSAGE);
    }
//weda neneeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee//doctor ekth awl giya
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse updateWard(@RequestBody WardDTO wardDTO) {
        wardService.updateWard(wardDTO);
        return new CommonResponse(OPERATION_SUCCESS, SUCCESS_MASSAGE);
    }

    @DeleteMapping(value = "/{wardsId}",produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse changeWardRole(@PathVariable("wardsId") Long wardsId) {
        wardService.changeWardStatus(wardsId);
        return new CommonResponse(OPERATION_SUCCESS, SUCCESS_MASSAGE);

    }

    @GetMapping(value = "/filter",produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse filterWards(@RequestParam(value = "name",required = false) String name){
        List<WardDTO> wardDTOS = wardService.filterWard(name);
        return new CommonResponse(OPERATION_SUCCESS, wardDTOS,SUCCESS_MASSAGE);

    }

}
