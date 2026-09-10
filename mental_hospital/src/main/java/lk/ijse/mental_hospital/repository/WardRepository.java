package lk.ijse.mental_hospital.repository;

import lk.ijse.mental_hospital.entity.Ward;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WardRepository extends JpaRepository<Ward,Long> {
    @Query(value = "SELECT * FROM wards WHERE ?1 IS NULL OR ward_name LIKE %?1%",nativeQuery = true)
    List<Ward> filterWard(String name);
}
