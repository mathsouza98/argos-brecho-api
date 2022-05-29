package com.brecho.argos.domain.models;

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
