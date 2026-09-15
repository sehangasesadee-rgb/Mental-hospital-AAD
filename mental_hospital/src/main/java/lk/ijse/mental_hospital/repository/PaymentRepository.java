package lk.ijse.mental_hospital.repository;

import lk.ijse.mental_hospital.entity.Bed;
import lk.ijse.mental_hospital.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment,Long> {
    @Query(value = "SELECT * FROM  payments WHERE ?1 IS NULL OR payment_id =?1 ",nativeQuery = true)
    List<Payment> filterPayment(long id);





}