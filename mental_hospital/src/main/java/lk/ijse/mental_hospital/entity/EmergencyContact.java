package lk.ijse.mental_hospital.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "emergency_contacts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmergencyContact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long emergencyContactId;

    @Column(nullable = false)
    private String contactName;

    @Column(nullable = false)
    private String relationship;

    @Column(nullable = false)
    private String contactNumber;

    @Column(nullable = false)
    private String address;

    @OneToOne
    @JoinColumn(name = "patient_id", nullable = false, unique = true)
    private Patient patient;
}