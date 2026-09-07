package lk.ijse.mental_hospital.repository;

import lk.ijse.mental_hospital.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department,Long> {
}
