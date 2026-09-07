package lk.ijse.mental_hospital.repository;

import lk.ijse.mental_hospital.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    @Query(value = "SELECT * FROM patients p WHERE (:name IS NULL OR p.patient_name LIKE CONCAT('%', :name, '%'))", nativeQuery = true)
    List<Patient> filterPatient(String name);

}
