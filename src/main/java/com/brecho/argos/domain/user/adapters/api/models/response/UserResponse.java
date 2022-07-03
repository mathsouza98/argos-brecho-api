package com.brecho.argos.domain.user.adapters.api.models.response;


import com.brecho.argos.domain.user.core.enums.Role;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class UserResponse {
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
