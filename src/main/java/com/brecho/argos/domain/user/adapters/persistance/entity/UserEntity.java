package com.brecho.argos.domain.user.adapters.persistance.entity;

import com.brecho.argos.domain.user.core.enums.Role;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "addresses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserEntity {
    @Id
    @Column(name = "id", length = 36)
    private String id;

    @Column(name = "username", length = 20)
    private String username;

    @Column(name = "password_hash", length = 100)
    private String password;

    @Column(name = "name", length = 50)
    private String name;

    @Column(name = "email", length = 50)
    private String email;

    @Column(name = "cpf", length = 36)
    private String cpf;

    @Column(name = "bithdate")
    private LocalDate birthdate;

    @Enumerated
    @Column(name = "role")
    private Role role;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "udpated_at")
    private LocalDateTime updatedAt;
}
