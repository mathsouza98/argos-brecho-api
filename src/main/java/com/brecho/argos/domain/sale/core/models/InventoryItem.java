package com.brecho.argos.domain.sale.core.models;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class InventoryItem {
    private String id;
    private int status;
    private int amount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Product product;
}
