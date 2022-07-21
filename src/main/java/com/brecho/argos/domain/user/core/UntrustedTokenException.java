package com.brecho.argos.domain.user.core;

public class UntrustedTokenException extends RuntimeException {
    public UntrustedTokenException() {
        super("Token cannot be trusted!");
    }
}
