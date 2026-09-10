package lk.ijse.mental_hospital.entity;

import jakarta.persistence.*;
import lk.ijse.mental_hospital.enumaration.WardStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "wards")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Ward {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long wardId;

    @Column(nullable = false, unique = true)
    private String wardName;

    @Column(nullable = false)
    private String wardType;

    @Column(nullable = false)
    private Integer capacity;

    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private WardStatus wardStatus = WardStatus.ACTIVE;
}