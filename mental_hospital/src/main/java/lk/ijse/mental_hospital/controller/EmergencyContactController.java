package lk.ijse.mental_hospital.controller;

import lk.ijse.mental_hospital.constant.CommonResponse;
import lk.ijse.mental_hospital.dto.EmergencyContactDTO;
import lk.ijse.mental_hospital.entity.EmergencyContact;
import lk.ijse.mental_hospital.repository.EmergencyContactRepository;
import lk.ijse.mental_hospital.repository.PatientRepository;
import lk.ijse.mental_hospital.service.EmergencyContactService;
import lk.ijse.mental_hospital.service.PatientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static lk.ijse.mental_hospital.constant.ResponsCode.OPERATION_SUCCESS;
import static lk.ijse.mental_hospital.constant.ResponseMassage.SUCCESS_MASSAGE;

@RestController
@RequestMapping("v1/emergencyContact")
@Slf4j
public class EmergencyContactController {
    private final EmergencyContactService  emergencyContactService;
    private final PatientService patientService;

    public EmergencyContactController(EmergencyContactService emergencyContactService, PatientService patientService) {
        this.emergencyContactService = emergencyContactService;
        this.patientService = patientService;
    }
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse addEmergencyContact(@RequestBody EmergencyContactDTO emergencyContactDTO) {
        emergencyContactService.saveEmergencyContact(emergencyContactDTO);
        return new CommonResponse(OPERATION_SUCCESS,SUCCESS_MASSAGE);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse getAllEmergencyContact(){
        return new CommonResponse(OPERATION_SUCCESS,emergencyContactService.getAllEmergencyContactId(),SUCCESS_MASSAGE);
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse updateEmergencyContact(@RequestBody EmergencyContactDTO emergencyContactDTO){
        emergencyContactService.UpdateEmergencyContact(emergencyContactDTO);
        return  new CommonResponse(OPERATION_SUCCESS,SUCCESS_MASSAGE);
    }

    @GetMapping(value = "/filter", produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse filterEmergencyContact(@RequestParam(value ="emergency_contacts_id",required = false )Long emergencyContactsId){
        List<EmergencyContactDTO> emergencyContactDTOS = emergencyContactService.filterEmergencyContact(emergencyContactsId);
        return new CommonResponse(OPERATION_SUCCESS,emergencyContactDTOS,SUCCESS_MASSAGE);
    }
}
