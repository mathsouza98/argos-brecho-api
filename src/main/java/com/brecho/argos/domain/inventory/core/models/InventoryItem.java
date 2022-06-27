package com.brecho.argos.domain.inventory.core.models;

import com.brecho.argos.domain.sale.core.enums.InventoryItemStatus;
import com.brecho.argos.domain.sale.core.models.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class InventoryItem {
    private String id;
    private InventoryItemStatus status;
    private int amount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Product product;
}
