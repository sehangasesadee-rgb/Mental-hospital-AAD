package lk.ijse.mental_hospital.repository;

import lk.ijse.mental_hospital.entity.Admission;
import lk.ijse.mental_hospital.entity.Bed;
import lk.ijse.mental_hospital.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AdmissionRepository extends JpaRepository<Admission, Long> {
    @Query(value = "SELECT * FROM admissions WHERE ?1 IS NULL OR admission_id = ?1",nativeQuery = true)
    List<Bed> filterAdmission(long id);



}
