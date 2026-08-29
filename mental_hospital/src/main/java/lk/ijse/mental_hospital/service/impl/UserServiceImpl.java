package lk.ijse.mental_hospital.service.impl;

import lk.ijse.mental_hospital.dto.UserDTO;
import lk.ijse.mental_hospital.entity.Role;
import lk.ijse.mental_hospital.entity.User;
import lk.ijse.mental_hospital.enumaration.UserStatus;
import lk.ijse.mental_hospital.repository.RoleRepository;
import lk.ijse.mental_hospital.repository.UserRepository;
import lk.ijse.mental_hospital.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public void saveUser(UserDTO userDTO) {

        log.info("saveUser");

        try {

            Role role = roleRepository.findById(userDTO.getRoleId())
                    .orElseThrow(() -> new RuntimeException("Role not found"));

            User user = new User();

            user.setUserName(userDTO.getUserName());
            user.setUserPassword(userDTO.getUserPassword());
            user.setUserStatus(UserStatus.ACTIVE);
            user.setRole(role);

            userRepository.save(user);

        } catch (Exception e) {
            log.error("saveUser error", e);
            throw e;
        }
    }

    @Override
    public List<UserDTO> getAllUsers() {
        try {
            List<UserDTO> userDTOList = new ArrayList<>();
            List<User> users = userRepository.findAll();
            for (User user : users) {
                UserDTO userDTO = new UserDTO();
                userDTO.setUserId(user.getUserId());
                userDTO.setUserName(user.getUserName());
                userDTO.setUserPassword(user.getUserPassword());
                userDTO.setUserStatus(user.getUserStatus().name());
                userDTO.setRoleId(user.getRole().getRoleId());

                userDTOList.add(userDTO);
            }
            return userDTOList;

        } catch (Exception e) {
            log.error("getAllUsers error");
            throw e;
        }
    }

    @Override
    public void updateUser(UserDTO userDTO) {
        log.info("updateUser");
        try {
            Optional<User> user = userRepository.findById(userDTO.getUserId());
            if (user.isEmpty()) {
                throw new RuntimeException("User not found");
            }
            User user1 = user.get();
            user1.setUserName(userDTO.getUserName());
            user1.setUserPassword(userDTO.getUserPassword());
            user1.setUserStatus(UserStatus.valueOf(userDTO.getUserStatus()));
            Role role = roleRepository.findById(userDTO.getRoleId())
                    .orElseThrow(() -> new RuntimeException("Role not found"));
            user1.setRole(role);
            userRepository.save(user1);

        }catch (Exception e){
            log.error("updateUser error");
            throw e;
        }
    }

    @Override
    public void changeUserStatus(long userId) {
        log.info("changeUserStatus");
        try {
            Optional<User> user = userRepository.findById(userId);
            if (user.isEmpty()) {
                throw new RuntimeException("User not found");
            }
            User user1 = user.get();
            user1.setUserStatus(UserStatus.INACTIVE);
            userRepository.save(user1);
        }catch (Exception e){
            log.error("changeUserStatus error");
            throw e;
        }
    }

    @Override
    public List<UserDTO> filterUser(String userName) {
        try {
            List<UserDTO> userDTOList = new ArrayList<>();
            List<User> users = userRepository.findByUserNameContaining(userName);
            for (User user : users) {
                UserDTO userDTO = new UserDTO();
                userDTO.setUserId(user.getUserId());
                userDTO.setUserName(user.getUserName());
                userDTO.setUserPassword(user.getUserPassword());
                userDTO.setUserStatus(user.getUserStatus().name());
                userDTO.setRoleId(user.getRole().getRoleId());
                userDTOList.add(userDTO);
            }
            return userDTOList;
        }catch (Exception e){
            log.error("filterUser error");
            throw e;
        }

    }

    @Override
    public void changeUserRole(long userId, long roleId) {
        log.info("changeUserRole");
        try {
            Optional<User> user = userRepository.findById(userId);
            if (user.isEmpty()) {
                throw new RuntimeException("User not found");
            }

            Role role = roleRepository.findById(roleId).orElseThrow(() -> new RuntimeException("Role not found"));
            User user1 = user.get();
            user1.setRole(role);
            userRepository.save(user1);

        } catch (Exception e) {
            log.error("changeUserRole error");
            throw e;
        }
    }

}