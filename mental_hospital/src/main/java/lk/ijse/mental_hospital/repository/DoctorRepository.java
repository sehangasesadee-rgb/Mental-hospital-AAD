package lk.ijse.mental_hospital.repository;

import lk.ijse.mental_hospital.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface DoctorRepository extends JpaRepository<Doctor,Long> {
    @Query (value = "SELECT * FROM doctors WHERE ?1 IS NULL OR doctor_name LIKE %?1%",nativeQuery = true)
    List<Doctor> filterDoctor(String name);
}
