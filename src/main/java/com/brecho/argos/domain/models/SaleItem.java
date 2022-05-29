package com.brecho.argos.domain.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaleItem {
    private String id;
    private Product product;
    private Sale sale;
    private int amount;
}
