package com.brecho.argos.domain.sale.core.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class SaleItem {
    private String id;
    private Product product;
    private int amount;
}
