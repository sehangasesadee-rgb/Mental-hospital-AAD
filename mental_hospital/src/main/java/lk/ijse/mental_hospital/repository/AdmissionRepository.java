package lk.ijse.mental_hospital.repository;

import lk.ijse.mental_hospital.entity.Admission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdmissionRepository extends JpaRepository<Admission,Long> {
}
