package com.brecho.argos.domain.sale.adapters.api.models.request;

import lombok.*;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SaleItemRequest {
    @NotNull(message = "Id do produto à ser comprado não pode ser nulo")
    private String productId;

    @NotNull(message = "Quantidade à ser comprada do produto não pode ser nula")
    @DecimalMin(value = "1", message = "Quantidade mínima deve ser igual à 1")
    private int amount;
}
