package lk.ijse.mental_hospital.controller;

import lk.ijse.mental_hospital.constant.CommonResponse;
import lk.ijse.mental_hospital.constant.ResponsCode;
import lk.ijse.mental_hospital.constant.ResponseMassage;
import lk.ijse.mental_hospital.dto.LoginDTO;
import lk.ijse.mental_hospital.dto.SUserDTO;
import lk.ijse.mental_hospital.dto.UserDTO;
import lk.ijse.mental_hospital.service.AuthenticationService;
import lk.ijse.mental_hospital.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final UserService userService;

    @PostMapping(
            value = "/login",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public CommonResponse login(@RequestBody LoginDTO loginDTO) {

        String token = authenticationService.login(loginDTO);
        UserDTO userDTO = authenticationService.getUserDTO(loginDTO.getUserName());

        SUserDTO sUserDTO = new SUserDTO();
        sUserDTO.setUserName(userDTO.getUserName());
        sUserDTO.setUserId(userDTO.getUserId());
        sUserDTO.setRoleId(userDTO.getRoleId());
        sUserDTO.setToken(token);
        sUserDTO.setUserId(userDTO.getUserId());
        sUserDTO.setUserStatus(userDTO.getUserStatus());



        return new CommonResponse(ResponsCode.OPERATION_SUCCESS,sUserDTO, ResponseMassage.SUCCESS_MASSAGE);
    }
}