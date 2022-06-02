package com.brecho.argos.domain.user.core.models;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Customer {
    private String id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Person person;
}
