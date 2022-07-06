package com.brecho.argos.factory.api.response;

import com.brecho.argos.domain.sale.adapters.api.mapper.JsonMapper;
import com.brecho.argos.domain.sale.adapters.api.models.response.ProductResponse;
import com.brecho.argos.domain.sale.adapters.api.models.response.SaleItemResponse;
import com.brecho.argos.domain.sale.adapters.api.models.response.SaleResponse;
import com.brecho.argos.domain.sale.core.models.ProductClassification;
import com.brecho.argos.domain.sale.core.models.Sale;
import com.brecho.argos.domain.user.adapters.api.models.response.UserResponse;
import com.brecho.argos.domain.user.core.enums.Role;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class SaleResponseFactory {
    public static SaleResponse createValidSaleResponse() {
        return SaleResponse
                .builder()
                .id("45ab68b5-42c0-49bc-9868-ea34258a89dd")
                .status(Sale.Status.WAITING_PAYMENT)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .totalValue(BigDecimal.valueOf(45.5))
                .buyer(UserResponse
                        .builder()
                        .username("alijesus")
                        .password("pass123")
                        .name("Alisson Jesus")
                        .email("alisson@jesus.com")
                        .cpf("34252287600")
                        .birthdate(LocalDate.of(1997, 3, 24))
                        .roles(List.of(Role.BUYER))
                        .createdAt(LocalDateTime.now())
                        .updatedAt(LocalDateTime.now())
                        .build())
                .saleItems(List.of(SaleItemResponse
                        .builder()
                        .id("66fa9a5d-f131-4d68-a490-b769905ad9ad")
                        .product(ProductResponse
                                .builder()
                                .id("525643da-9e92-497e-b624-6e34becef20a")
                                .name("Faca Tramontina")
                                .price(BigDecimal.valueOf(45.5))
                                .createdAt(LocalDateTime.now())
                                .updatedAt(LocalDateTime.now())
                                .seller(UserResponse
                                        .builder()
                                        .id("92dd6b47-3761-4657-927d-676c7d8b7146")
                                        .username("pvendedor")
                                        .password("123")
                                        .name("Pedro vendedor")
                                        .email("pedro@vendedor.com")
                                        .cpf("10289700564")
                                        .birthdate(LocalDate.of(1998, 8, 27))
                                        .roles(List.of(Role.SELLER))
                                        .createdAt(LocalDateTime.now())
                                        .updatedAt(LocalDateTime.now())
                                        .build())
                                .productClassification(ProductClassification
                                        .builder()
                                        .id("67a62ced-1846-45f6-bdd4-2d46c0685066")
                                        .value("Facas")
                                        .build())
                                .build())
                        .amount(3)
                        .build()))
                .build();
    }
}
