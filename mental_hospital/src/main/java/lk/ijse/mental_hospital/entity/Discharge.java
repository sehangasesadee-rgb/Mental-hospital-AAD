package lk.ijse.mental_hospital.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "discharges")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Discharge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dischargeId;

    @Column(nullable = false)
    private LocalDate dischargeDate;

    @Column(length = 500)
    private String remarks;

    @Column(nullable = false)
    private String dischargeStatus;

    @OneToOne
    @JoinColumn(name = "admission_id", nullable = false, unique = true)
    private Admission admission;

    @ManyToOne
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor;
}