package com.brecho.argos.domain.sale.core.models;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class InventoryItem {
    private String id;
    private int status;
    private int amount;
    private LocalDate updatedAt;
    private Product product;
}
