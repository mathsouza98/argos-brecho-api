package com.brecho.argos.factory;

import com.brecho.argos.domain.user.core.enums.Role;
import com.brecho.argos.domain.user.core.models.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class UserFactory {

    public static User createValidBuyer() {
        return User
                .builder()
                .id("0e9ad18e-799a-4021-9812-c38e2d8b2007")
                .username("alijesus")
                .password("pass123")
                .name("Alisson Jesus")
                .email("alisson@jesus.com")
                .cpf("34252287600")
                .birthdate(LocalDate.of(1997, 3, 24))
                .roles(List.of(Role.BUYER))
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }
}
