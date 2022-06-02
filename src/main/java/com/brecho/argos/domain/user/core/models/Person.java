package com.brecho.argos.domain.user.core.models;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class Person {
    private String id;
    private String name;
    private String email;
    private String cpf;
    private LocalDate birthdate;
}
