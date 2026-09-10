package lk.ijse.mental_hospital.repository;

import lk.ijse.mental_hospital.entity.Nurse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface NurseRepository extends JpaRepository<Nurse,Long> {
    @Query(value = """
            SELECT * FROM nurses
            WHERE nurse_name LIKE CONCAT('%', ?1, '%')
            """, nativeQuery = true)
    List<Nurse> findAllByNurseName(String nurseName);
}
