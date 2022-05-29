package com.brecho.argos.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Role {
    SELLER(1),
    ADMIN(2);

    private final int code;
}
