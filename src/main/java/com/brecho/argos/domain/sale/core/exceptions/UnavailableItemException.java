package com.brecho.argos.domain.sale.core.exceptions;

import java.util.List;

public class UnavailableItemException extends RuntimeException {
    public UnavailableItemException(List<String> productIds) {
        super("Produto indisponível em estoque, Products={%s}".formatted(productIds));
    }
}
