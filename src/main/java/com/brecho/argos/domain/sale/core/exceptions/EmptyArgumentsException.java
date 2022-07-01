package com.brecho.argos.domain.sale.core.exceptions;

public class EmptyArgumentsException extends RuntimeException {
    public EmptyArgumentsException(String params) {
        super("Parâmetro(s) Params={%s} não pode ser vazio!".formatted(params));
    }
}
