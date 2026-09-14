package lk.ijse.mental_hospital.entity;

import jakarta.persistence.*;
import lk.ijse.mental_hospital.enumaration.AdmissionStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "admissions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Admission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long admissionId;

    @Column(nullable = false)
    private LocalDate admissionDate;

    @Column(length = 500)
    private String reason;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private AdmissionStatus admissionStatus;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "ward_id", nullable = false)
    private Ward ward;

    @ManyToOne
    @JoinColumn(name = "bed_id")
    private Bed bed;

    @ManyToOne
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor;
}