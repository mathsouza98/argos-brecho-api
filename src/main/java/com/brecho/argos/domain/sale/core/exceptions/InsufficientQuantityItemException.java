package com.brecho.argos.domain.sale.core.exceptions;

import com.brecho.argos.domain.sale.core.models.SaleItem;

public class InsufficientQuantityItemException extends RuntimeException {
    public InsufficientQuantityItemException(SaleItem saleItem) {
        super("Quantidade não disponível em estoque para o item {%s}".formatted(saleItem));
    }
}
