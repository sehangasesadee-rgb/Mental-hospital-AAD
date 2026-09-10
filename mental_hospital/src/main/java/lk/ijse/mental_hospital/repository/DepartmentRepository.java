package lk.ijse.mental_hospital.repository;

import jakarta.validation.constraints.DecimalMax;
import lk.ijse.mental_hospital.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department,Long> {
    @Query(value = "SELECT * FROM departments WHERE ?1 IS NULL OR department_name LIKE %?1%",nativeQuery = true)
    List<Department> filterDepartment(String departmentName);
}
