package com.brecho.argos.domain.sale.core.models;

import com.brecho.argos.domain.user.core.models.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class Sale {
    private String id;
    private Status status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private BigDecimal totalValue;
    private User buyer;
    private List<SaleItem> saleItems;

    public enum Status {
        APPROVED, WAITING_PAYMENT, CANCELLED
    }
}
