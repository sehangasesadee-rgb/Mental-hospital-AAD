package lk.ijse.mental_hospital.entity;

import jakarta.persistence.*;
import lk.ijse.mental_hospital.enumaration.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false, unique = true)
    private String userName;

    @Column(nullable = false)
    private String userPassword;

    @Enumerated(EnumType.STRING)
    private UserStatus userStatus;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;
}