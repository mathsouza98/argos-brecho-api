package com.brecho.argos.domain.sale.adapters.api.models.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SaleItemResponse {
    private String id;
    private ProductResponse product;
    private int amount;
}
