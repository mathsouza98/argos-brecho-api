package com.brecho.argos.domain.sale.core.exceptions;

public class BuyerCannotBeSellerException extends RuntimeException {
    public BuyerCannotBeSellerException() {
        super("Usuário não pode comprar um produto que ele mesmo anúnciou");
    }
}
