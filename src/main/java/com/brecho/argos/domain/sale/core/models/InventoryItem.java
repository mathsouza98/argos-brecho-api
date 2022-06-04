package com.brecho.argos.domain.sale.core.models;

import com.brecho.argos.domain.sale.core.enums.InventoryItemStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class InventoryItem {
    private String id;
    private InventoryItemStatus status;
    private int amount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Product product;
}
