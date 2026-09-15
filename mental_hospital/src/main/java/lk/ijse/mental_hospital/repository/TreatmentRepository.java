package lk.ijse.mental_hospital.repository;

import lk.ijse.mental_hospital.entity.Prescription;
import lk.ijse.mental_hospital.entity.Treatment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TreatmentRepository extends JpaRepository<Treatment,Long> {
    @Query(value = "SELECT * FROM treatments WHERE ?1 IS NULL OR treatment_id = ?1", nativeQuery = true)
    List<Treatment> filterTreatments(Long treatment_id);
}
