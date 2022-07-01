package com.brecho.argos.domain.user.core;

import com.brecho.argos.domain.user.core.models.User;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(User user) {
        super("Usuário User={%s} não encontrado".formatted(user));
    }
}
