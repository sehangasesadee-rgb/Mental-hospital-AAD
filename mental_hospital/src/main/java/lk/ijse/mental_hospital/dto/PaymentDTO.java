package lk.ijse.mental_hospital.dto;

import lk.ijse.mental_hospital.enumaration.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDTO {

    private Long paymentId;
    private BigDecimal amount;
    private LocalDate paymentDate;
    private String paymentMethod;
    private PaymentStatus paymentStatus;
    private Long patientId;
    private Long appointmentId;
    private Long admissionId;
}