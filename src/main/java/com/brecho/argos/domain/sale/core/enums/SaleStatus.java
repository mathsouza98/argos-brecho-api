package com.brecho.argos.domain.sale.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum SaleStatus {
    APPROVED(1),
    WAITING_PAYMENT(2),
    CANCELLED(3);

    private final int code;
}
