package lk.ijse.mental_hospital.repository;

import lk.ijse.mental_hospital.entity.Bed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BedRepository extends JpaRepository<Bed, Long> {
    @Query(value = """
            SELECT * FROM beds
            WHERE ?1 IS NULL
            OR bed_number LIKE CONCAT('%', ?1, '%')
            """, nativeQuery = true)
    List<Bed> filterBed(String bedNumber);
}
