package com.brecho.argos.domain.sale.core.exceptions;

public class InvalidSaleException extends RuntimeException {
    public InvalidSaleException(Exception e) {
        super(e);
    }
}
