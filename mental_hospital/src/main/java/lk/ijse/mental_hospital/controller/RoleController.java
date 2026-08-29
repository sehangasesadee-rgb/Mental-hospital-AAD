package lk.ijse.mental_hospital.controller;


import lk.ijse.mental_hospital.constant.CommonResponse;
import lk.ijse.mental_hospital.dto.RoleDTO;
import lk.ijse.mental_hospital.service.RoleService;
import lk.ijse.mental_hospital.service.impl.RoleServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static lk.ijse.mental_hospital.constant.ResponsCode.OPERATION_SUCCESS;
import static lk.ijse.mental_hospital.constant.ResponseMassage.SUCCESS_MASSAGE;

@RestController
@RequestMapping("v1/roles")
@Slf4j
public class RoleController {
    private final RoleService roleService;
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse saveRole(@RequestBody RoleDTO roleDTO) {
        roleService.saveRole(roleDTO);
        return new CommonResponse(OPERATION_SUCCESS,SUCCESS_MASSAGE);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse getAllRoles() {
        return new CommonResponse(OPERATION_SUCCESS,roleService.findAllRole(),SUCCESS_MASSAGE);
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse updateRole(@RequestBody RoleDTO roleDTO) {
        roleService.updateRole(roleDTO);
        return new CommonResponse(OPERATION_SUCCESS,SUCCESS_MASSAGE);
    }

    @DeleteMapping(value = "/{roleId}",produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse deleteRole(@PathVariable long roleId) {
        roleService.changeRole(roleId);
        return new CommonResponse(OPERATION_SUCCESS,SUCCESS_MASSAGE);
    }

    @GetMapping(value = "/filter" , produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse FilterRole(@RequestParam(value = "RoleName",required = false)String RoleName) {
        List<RoleDTO> roleDTOList = roleService.filterRole(RoleName);
        return new CommonResponse(OPERATION_SUCCESS,roleDTOList,SUCCESS_MASSAGE);
    }
}
