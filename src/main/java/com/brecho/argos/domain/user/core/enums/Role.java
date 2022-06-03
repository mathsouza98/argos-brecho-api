package com.brecho.argos.domain.user.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Role {
    ADMIN(1),
    SELLER(2),
    BUYER(3);

    private final int code;
}
