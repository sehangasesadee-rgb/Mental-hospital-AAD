package lk.ijse.mental_hospital.repository;

import lk.ijse.mental_hospital.entity.Doctor;
import org.springframework.data.repository.CrudRepository;

public interface DoctorRepository extends CrudRepository<Doctor,Long> {
}
