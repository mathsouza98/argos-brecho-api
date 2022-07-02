package com.brecho.argos.domain.sale.adapters.api.models.response;

import com.brecho.argos.domain.sale.core.models.Product;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SaleItemResponse {
    private String id;
    private Product product;
    private int amount;
}
