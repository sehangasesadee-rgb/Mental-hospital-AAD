package lk.ijse.mental_hospital.service.impl;

import lk.ijse.mental_hospital.dto.RoleDTO;
import lk.ijse.mental_hospital.entity.Role;
import lk.ijse.mental_hospital.enumaration.RoleStatus;
import lk.ijse.mental_hospital.repository.RoleRepository;
import lk.ijse.mental_hospital.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }
    @Override
    public void saveRole(RoleDTO roleDTO) {
        log.info("saveRole");

         try {
            Role role = new Role();
            role.setRoleName(roleDTO.getRoleName());
             role.setRoleStatus(RoleStatus.ACTIVE);

            roleRepository.save(role);
        }catch (Exception e){
             log.error("saveRole error");
         }


    }

    @Override
    public List<RoleDTO> findAllRole() {
        try {
            List<RoleDTO> roleDTOList = new ArrayList<>();
//            List<Role> roleList = roleRepository.findAll();
            List<Role> roleList = roleRepository.findByRoleStatus(RoleStatus.ACTIVE);
            for (Role role : roleList) {
                RoleDTO roleDTO = new RoleDTO();
                roleDTO.setRoleId(role.getRoleId());
                roleDTO.setRoleName(role.getRoleName());
                roleDTOList.add(roleDTO);
            }return roleDTOList;
        } catch (Exception e) {
            log.error("findAllRole error");
            throw e;
        }

    }

    @Override
    public void updateRole(RoleDTO roleDTO) {
        log.info("updateRole");
        try {
            Optional<Role> role = roleRepository.findById(roleDTO.getRoleId());
            if(role.isEmpty()){
                throw new Exception("role not found");

            }
            Role role1 = role.get();
            role1.setRoleName(roleDTO.getRoleName());
            roleRepository.save(role.get());
        }catch (Exception e){
            log.error("updateRole error");
        }

    }

    @Override
    public void changeRole(long roleId) {
        log.info("changeRole");
        try {
            Optional<Role> role = roleRepository.findById(roleId);
            if (role.isEmpty()) {
                throw new RuntimeException("role not found");
            }
            Role role1 = role.get();
            role1.setRoleStatus(RoleStatus.INACTIVE);
            roleRepository.save(role.get());

        } catch (Exception e) {
            log.error("changeRole error");
        }

    }

    @Override
    public List<RoleDTO> filterRole(String roleName) {

        try {
            List<RoleDTO> roleDTOList = new ArrayList<>();
            List<Role> roles = roleRepository.filterRole(roleName);

            for (Role role : roles) {
                RoleDTO roleDTO = new RoleDTO(
                        role.getRoleId(),
                        role.getRoleName()
                );
                roleDTOList.add(roleDTO);
            }
            return roleDTOList;
        }catch (Exception e){
            log.error("filterRole error");
            throw e;
        }
    }
}
