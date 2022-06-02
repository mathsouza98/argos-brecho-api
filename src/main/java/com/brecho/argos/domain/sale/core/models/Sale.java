package com.brecho.argos.domain.sale.core.models;

import com.brecho.argos.domain.user.core.models.Customer;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class Sale {
    private String id;
    private LocalDateTime createdAt;
    private BigDecimal totalValue;
    private Customer customer;
}
