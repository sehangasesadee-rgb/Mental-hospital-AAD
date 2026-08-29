package lk.ijse.mental_hospital.repository;

import lk.ijse.mental_hospital.entity.User;
import lk.ijse.mental_hospital.enumaration.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUserName(String userName);

    List<User> findByUserNameContaining(String userName);

    List<User> findByUserStatus(UserStatus userStatus);
}