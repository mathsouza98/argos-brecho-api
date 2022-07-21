package com.brecho.argos.domain.user.core;

public class InvalidCredentialsExceptions extends RuntimeException {
    public InvalidCredentialsExceptions() {
        super("As credenciais do usuário não são válidas!");
    }
}
