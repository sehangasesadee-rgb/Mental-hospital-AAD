package lk.ijse.mental_hospital.controller;

import lk.ijse.mental_hospital.constant.CommonResponse;
import lk.ijse.mental_hospital.dto.UserDTO;
import lk.ijse.mental_hospital.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static lk.ijse.mental_hospital.constant.ResponsCode.OPERATION_SUCCESS;
import static lk.ijse.mental_hospital.constant.ResponseMassage.SUCCESS_MASSAGE;

@RestController
@RequestMapping("v1/users")
@Slf4j
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse saveUser(@RequestBody UserDTO userDTO) {
        userService.saveUser(userDTO);
        return new CommonResponse(OPERATION_SUCCESS, SUCCESS_MASSAGE);
    }

//    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
//    public CommonResponse getAllUsers() {
//        userService.getAllUsers();
//        return new CommonResponse(OPERATION_SUCCESS,getAllUsers(), SUCCESS_MASSAGE);
//    }
@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public CommonResponse getAllUsers() {

    List<UserDTO> userDTOList = userService.getAllUsers();

    return new CommonResponse(OPERATION_SUCCESS, userDTOList, SUCCESS_MASSAGE);
}

@PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse updateUser(@RequestBody UserDTO userDTO) {
        userService.updateUser(userDTO);
        return new CommonResponse(OPERATION_SUCCESS, SUCCESS_MASSAGE);
}

    @DeleteMapping(value = "/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse changeUserStatus(@PathVariable long userId) {
        userService.changeUserStatus(userId);
        return new CommonResponse(OPERATION_SUCCESS, SUCCESS_MASSAGE);
    }

//    @GetMapping(value = "/filter",produces = MediaType.APPLICATION_JSON_VALUE)
//    public CommonResponse filterUser(@RequestParam(required = false)String UserName) {
//        List<UserDTO> userDTOList = userService.filterUser(UserName);
//        return new CommonResponse(OPERATION_SUCCESS, userDTOList, SUCCESS_MASSAGE);
//    }

    @GetMapping(value = "/filter", produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse filterUser(@RequestParam(value = "userName", required = false) String userName) {
        List<UserDTO> userDTOList = userService.filterUser(userName);
        return new CommonResponse(OPERATION_SUCCESS, userDTOList, SUCCESS_MASSAGE);
    }

    @PatchMapping("/{userId}/role/{roleId}")
    public CommonResponse changeUserRole(@PathVariable long userId, @PathVariable long roleId) {
        userService.changeUserRole(userId, roleId);
        return new CommonResponse(OPERATION_SUCCESS, SUCCESS_MASSAGE);
    }

}

