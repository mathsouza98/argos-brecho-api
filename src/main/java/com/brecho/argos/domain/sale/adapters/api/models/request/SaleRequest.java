package com.brecho.argos.domain.sale.adapters.api.models.request;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SaleRequest {
    @NotNull(message = "É preciso adicionar pelo menos um item na lista de compras")
    private List<SaleItemRequest> saleItems;
}
