package com.brecho.argos.factory.api.request;

import com.brecho.argos.domain.sale.adapters.api.models.request.SaleItemRequest;
import com.brecho.argos.domain.sale.adapters.api.models.request.SaleRequest;

import java.util.List;

public class SaleRequestFactory {
    public static SaleRequest createValidSaleRequest() {
        return SaleRequest
                .builder()
                .saleItems(List.of(SaleItemRequest
                        .builder()
                        .productId("525643da-9e92-497e-b624-6e34becef20a")
                        .amount(3)
                        .build()))
                .build();
    }

    public static String createValidBuyerId() {
        return "58fb04da-0e47-4e5e-b7e4-3794fd3de1d1";
    }
}
