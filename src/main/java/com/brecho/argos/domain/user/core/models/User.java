package com.brecho.argos.domain.user.core.models;

import com.brecho.argos.domain.user.core.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class User {
    private String id;
    private String username;
    private String password;
    private String name;
    private String email;
    private String cpf;
    private LocalDate birthdate;
    private List<Role> roles;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}