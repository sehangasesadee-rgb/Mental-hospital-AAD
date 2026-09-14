package lk.ijse.mental_hospital.repository;

import lk.ijse.mental_hospital.entity.Bed;
import lk.ijse.mental_hospital.entity.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PrescriptionRepository extends JpaRepository<Prescription,Long> {

    @Query(value = "SELECT * FROM prescriptions WHERE ?1 IS NULL OR prescription_id = ?1", nativeQuery = true)
    List<Prescription> filterPrescription(Long id);

}
