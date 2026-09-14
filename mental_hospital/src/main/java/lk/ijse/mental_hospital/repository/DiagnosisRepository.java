package lk.ijse.mental_hospital.repository;

import lk.ijse.mental_hospital.entity.Diagnosis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.tools.Diagnostic;
import java.util.List;
//import java.awt.*;

public interface DiagnosisRepository extends JpaRepository<Diagnosis,Long> {
    @Query(value = "SELECT * FROM diagnoses WHERE ?1 IS NULL OR patient_id LIKE %?1%",nativeQuery = true)
    List<Diagnosis> filterDiagnosis(Long id);
}
