package lk.ijse.mental_hospital.entity;

import jakarta.persistence.*;
import lk.ijse.mental_hospital.enumaration.BedStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "beds")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Bed {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bedId;

    @Column(nullable = false, unique = true)
    private String bedNumber;

    @Column(nullable = false)
    private String bedType;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private BedStatus bedStatus;

    @ManyToOne
    @JoinColumn(name = "ward_id", nullable = false)
    private Ward ward;
}