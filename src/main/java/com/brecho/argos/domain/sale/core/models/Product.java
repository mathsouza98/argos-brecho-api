package com.brecho.argos.domain.sale.core.models;

import com.brecho.argos.domain.user.core.models.Advertiser;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class Product {
    private String id;
    private String name;
    private BigDecimal price;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Advertiser advertiser;
    private ProductClassification productClassification;
}
