package lk.ijse.mental_hospital.service;

import lk.ijse.mental_hospital.dto.PaymentDTO;
import lk.ijse.mental_hospital.entity.Payment;

import java.util.List;

public interface PaymentService {

    void savePayment(PaymentDTO paymentDTO);

    List<PaymentDTO> getAllPayments();

    void updatePayment(PaymentDTO paymentDTO);

    List<PaymentDTO> filerPayment(Long id);

    void deletePayment(Long id);
}
