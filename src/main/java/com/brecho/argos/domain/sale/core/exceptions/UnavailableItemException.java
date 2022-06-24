package com.brecho.argos.domain.sale.core.exceptions;

import com.brecho.argos.domain.sale.core.models.Product;

public class UnavailableItemException extends RuntimeException {
    public UnavailableItemException(Product product) {
        super("Produto indisponível em estoque, Product={%s}".formatted(product));
    }
}
