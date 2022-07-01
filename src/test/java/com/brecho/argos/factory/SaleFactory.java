package com.brecho.argos.factory;

import com.brecho.argos.domain.sale.core.models.*;
import com.brecho.argos.domain.user.core.enums.Role;
import com.brecho.argos.domain.user.core.models.User;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class SaleFactory {
    public static Sale createValidSale() {
        return Sale
                .builder()
                .id("45ab68b5-42c0-49bc-9868-ea34258a89dd")
                .totalValue(BigDecimal.valueOf(45.5))
                .buyer(User
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
                .saleItems(List.of(SaleItem
                        .builder()
                        .id("66fa9a5d-f131-4d68-a490-b769905ad9ad")
                        .product(Product
                                    .builder()
                                    .id("525643da-9e92-497e-b624-6e34becef20a")
                                    .name("Faca Tramontina")
                                    .price(BigDecimal.valueOf(45.5))
                                    .createdAt(LocalDateTime.now())
                                    .updatedAt(LocalDateTime.now())
                                    .seller(User
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

    public static Sale createInvalidSaleBySaleItemQuantityBiggerThanAvailableInInventory() {
        return Sale
                .builder()
                .id("45ab68b5-42c0-49bc-9868-ea34258a89dd")
                .totalValue(BigDecimal.valueOf(45.5))
                .buyer(User
                        .builder()
                        .id("58fb04da-0e47-4e5e-b7e4-3794fd3de1d1")
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
                .saleItems(List.of(SaleItem
                        .builder()
                        .id("66fa9a5d-f131-4d68-a490-b769905ad9ad")
                        .product(Product
                                .builder()
                                .id("525643da-9e92-497e-b624-6e34becef20a")
                                .name("Faca Tramontina")
                                .price(BigDecimal.valueOf(45.5))
                                .createdAt(LocalDateTime.now())
                                .updatedAt(LocalDateTime.now())
                                .seller(User
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
                        .amount(40)
                        .build()))
                .build();
    }

    public static Sale createInvalidSaleByBuyerAndSellerBeingTheSame() {
        return Sale
                .builder()
                .id("45ab68b5-42c0-49bc-9868-ea34258a89dd")
                .totalValue(BigDecimal.valueOf(45.5))
                .buyer(User
                        .builder()
                        .id("58fb04da-0e47-4e5e-b7e4-3794fd3de1d1")
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
                .saleItems(List.of(SaleItem
                        .builder()
                        .id("66fa9a5d-f131-4d68-a490-b769905ad9ad")
                        .product(Product
                                .builder()
                                .id("525643da-9e92-497e-b624-6e34becef20a")
                                .name("Faca Tramontina")
                                .price(BigDecimal.valueOf(45.5))
                                .createdAt(LocalDateTime.now())
                                .updatedAt(LocalDateTime.now())
                                .seller(User
                                        .builder()
                                        .id("58fb04da-0e47-4e5e-b7e4-3794fd3de1d1")
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

    public static List<String> createUnavailableProductIds() {
        return List.of("c7c611f2-e296-4c3b-be5e-96158ce1e467", "eaaadb44-2ffc-4a4e-91ed-77657eaa33da");
    }
}
