package com.brecho.argos.domain.sale.adapters.api.models.response;

import com.brecho.argos.domain.sale.core.models.Sale;
import com.brecho.argos.domain.user.adapters.api.models.response.UserResponse;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SaleResponse {
    private String id;
    private Sale.Status status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private BigDecimal totalValue;
    private UserResponse buyer;
    private List<SaleItemResponse> saleItems;
}
