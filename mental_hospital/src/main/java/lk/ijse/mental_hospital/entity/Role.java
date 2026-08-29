package lk.ijse.mental_hospital.entity;

import jakarta.persistence.*;
import lk.ijse.mental_hospital.enumaration.RoleStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "roles")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleId;

    @Column(nullable = false, unique = true)
    private String roleName;

    @Enumerated(EnumType.STRING)
    private RoleStatus roleStatus;
}