package lk.ijse.mental_hospital.repository;

import lk.ijse.mental_hospital.entity.Admission;
import lk.ijse.mental_hospital.entity.Discharge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DischargeRepository extends JpaRepository<Discharge,Long> {

    @Query(value = "SELECT * FROM discharges WHERE ?1 IS NULL OR discharge_id = ?1", nativeQuery = true)
    List<Discharge> filterDischarge(Long id);
}
