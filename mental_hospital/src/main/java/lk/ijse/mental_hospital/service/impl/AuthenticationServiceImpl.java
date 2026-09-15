package lk.ijse.mental_hospital.service.impl;

import lk.ijse.mental_hospital.dto.LoginDTO;
import lk.ijse.mental_hospital.dto.UserDTO;
import lk.ijse.mental_hospital.entity.User;
import lk.ijse.mental_hospital.repository.UserRepository;
import lk.ijse.mental_hospital.security.JwtUtil;
import lk.ijse.mental_hospital.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AuthenticationManager authenticationManager;

    private final UserRepository userRepository;

    private final JwtUtil jwtUtil;

    @Override
    public String login(LoginDTO loginDTO) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDTO.getUserName(),
                        loginDTO.getUserPassword()
                )
        );

        User user = userRepository
                .findByUserName(loginDTO.getUserName())
                .orElseThrow(() ->
                        new RuntimeException("User not found")
                );

        return jwtUtil.generateToken(
                new lk.ijse.mental_hospital.dto.UserDTO(
                        user.getUserId(),
                        user.getUserName(),
                        user.getUserPassword(),
                        user.getUserStatus(),
                        user.getRole().getRoleId()
                )
        );
    }

    @Override
    public UserDTO getUserDTO(String name) {

        Optional<User> user = userRepository.findByUserName(name);
        if(user.isEmpty()){
            throw new RuntimeException("User not found");
        }
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(user.get().getUserId());
        userDTO.setUserName(user.get().getUserName());
        userDTO.setUserPassword(user.get().getUserPassword());
        userDTO.setUserStatus(user.get().getUserStatus());
        userDTO.setRoleId(user.get().getRole().getRoleId());
        return userDTO;

    }
}