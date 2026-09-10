package lk.ijse.mental_hospital.repository;

import lk.ijse.mental_hospital.entity.Appointment;
import lk.ijse.mental_hospital.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AppoinmentRepository extends JpaRepository<Appointment,Long> {
    @Query(value = """
        SELECT * FROM appointments
        WHERE (?1 IS NULL OR appointment_id = ?1)
        AND (?2 IS NULL OR patient_id = ?2)
        """, nativeQuery = true)
    List<Appointment> filterAppoinments(Long appointmentId, Long patientId);
}
