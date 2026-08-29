package lk.ijse.mental_hospital.repository;

import lk.ijse.mental_hospital.entity.Role;
import lk.ijse.mental_hospital.enumaration.RoleStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByRoleName(String roleName);

    List<Role> findByRoleNameContaining(String roleName);
    List<Role> findByRoleStatus(RoleStatus roleStatus);

    @Query(value = "SELECT * FROM roles WHERE (?1 IS NULL OR role_name LIKE CONCAT('%', ?1, '%'))", nativeQuery = true)
    List<Role> filterRole(String roleName);
}