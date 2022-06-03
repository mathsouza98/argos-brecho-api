package com.brecho.argos.domain.sale.core.models;

import com.brecho.argos.domain.user.core.models.User;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class Sale {
    private String id;
    private Status status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private BigDecimal totalValue;
    private User customer;

    public enum Status {
        APPROVED, WAITING_PAYMENT, CANCELLED
    }
}
