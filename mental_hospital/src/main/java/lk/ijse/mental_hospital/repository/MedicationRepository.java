package lk.ijse.mental_hospital.repository;

import lk.ijse.mental_hospital.entity.Bed;
import lk.ijse.mental_hospital.entity.Medication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicationRepository extends JpaRepository<Medication, Long> {

    @Query(value = "SELECT  * FROM medications WHERE ?1 IS NULL OR Medication_id LIKE %?1%", nativeQuery = true)
    List<Medication> filterMedication(Long id);
}