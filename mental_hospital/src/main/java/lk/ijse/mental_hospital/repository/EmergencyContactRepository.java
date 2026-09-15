package lk.ijse.mental_hospital.repository;

import lk.ijse.mental_hospital.entity.Discharge;
import lk.ijse.mental_hospital.entity.EmergencyContact;
import lk.ijse.mental_hospital.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmergencyContactRepository extends JpaRepository<EmergencyContact,Long> {

    @Query(value = "SELECT * FROM emergency_contacts WHERE ?1 IS NULL OR emergency_contact_id = ?1", nativeQuery = true)
    List<EmergencyContact> findEmergencyContactId(Long id);

}
