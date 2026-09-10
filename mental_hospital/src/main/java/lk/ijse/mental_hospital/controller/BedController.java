package lk.ijse.mental_hospital.controller;

import lk.ijse.mental_hospital.constant.CommonResponse;
import lk.ijse.mental_hospital.dto.BedDTO;
import lk.ijse.mental_hospital.service.BedService;
import lk.ijse.mental_hospital.service.WardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static lk.ijse.mental_hospital.constant.ResponsCode.OPERATION_SUCCESS;
import static lk.ijse.mental_hospital.constant.ResponseMassage.SUCCESS_MASSAGE;

@RestController
@RequestMapping("v1/beds")
@Slf4j
public class BedController {
    private final BedService bedService;
    private final WardService wardService;
    public BedController(BedService bedService, WardService wardService) {
        this.bedService = bedService;
        this.wardService = wardService;
    }
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse addBed(@RequestBody BedDTO bedDTO){
        bedService.saveBed(bedDTO);
        return new CommonResponse(OPERATION_SUCCESS, SUCCESS_MASSAGE);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse getfindAll(){
        return new CommonResponse(OPERATION_SUCCESS,bedService.findAll(), SUCCESS_MASSAGE);
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse updateBed(@RequestBody BedDTO bedDTO){
        bedService.updateBed(bedDTO);
        return new CommonResponse(OPERATION_SUCCESS, SUCCESS_MASSAGE);
    }

    @DeleteMapping(value = "/{bedId}",produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse deleteBed(@PathVariable long bedId){
        bedService.deleteBed(bedId);
        return new CommonResponse(OPERATION_SUCCESS, SUCCESS_MASSAGE);
    }

    @GetMapping(value = "/filter",produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse filterBed(@RequestParam (value = "bedNumber",required = false) String bedNumber){
        List<BedDTO> bedDTOS = bedService.filterBed(bedNumber);
        return new CommonResponse(OPERATION_SUCCESS,bedDTOS,SUCCESS_MASSAGE);
    }
}
