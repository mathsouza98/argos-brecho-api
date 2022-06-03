package com.brecho.argos.domain.sale.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum InventoryItemStatus {
    AVAILABLE(1),
    UNAVAILABLE(2);

    private final int code;
}
