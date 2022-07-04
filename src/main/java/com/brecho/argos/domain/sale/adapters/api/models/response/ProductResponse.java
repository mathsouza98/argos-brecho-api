package com.brecho.argos.domain.sale.adapters.api.models.response;

import com.brecho.argos.domain.sale.core.models.ProductClassification;
import com.brecho.argos.domain.user.adapters.api.models.response.UserResponse;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class ProductResponse {
    private String id;
    private String name;
    private BigDecimal price;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private UserResponse seller;
    private ProductClassification productClassification;
}
