package com.brecho.argos.domain.models;

import com.brecho.argos.domain.enums.Role;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Admin {
    private String id;
    private String username;
    private String password;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Role role;
    private Person person;
}

