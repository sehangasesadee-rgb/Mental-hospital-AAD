package lk.ijse.mental_hospital.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "medications")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Medication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long medicationId;

    @Column(nullable = false, unique = true)
    private String medicationName;

    @Column(nullable = false)
    private String dosage;

    @Column(length = 500)
    private String description;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private String medicationStatus;
}