package com.brecho.argos.domain.sale.core.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaleItem {
    private String id;
    private Product product;
    private int amount;
}
