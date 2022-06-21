package com.brecho.argos.factory;

import com.brecho.argos.domain.sale.adapters.persistance.entity.*;
import com.brecho.argos.domain.sale.core.enums.InventoryItemStatus;
import com.brecho.argos.domain.user.adapters.persistance.entity.UserEntity;
import com.brecho.argos.domain.user.core.enums.Role;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class SaleEntityFactory {
    public static List<InventoryItemEntity> createValidAvailableInventoryItems() {
        return List.of(
                InventoryItemEntity
                        .builder()
                        .id("712b595f-b83f-43a7-8858-90d7f6f54aca")
                        .status(InventoryItemStatus.AVAILABLE)
                        .amount(33)
                        .createdAt(LocalDateTime.now())
                        .updatedAt(LocalDateTime.now())
                        .product(ProductEntity
                            .builder()
                            .id("525643da-9e92-497e-b624-6e34becef20a")
                            .name("Faca Tramontina")
                            .price(BigDecimal.valueOf(45.5))
                            .createdAt(LocalDateTime.now())
                            .updatedAt(LocalDateTime.now())
                            .seller(UserEntity
                                    .builder()
                                    .id("92dd6b47-3761-4657-927d-676c7d8b7146")
                                    .username("pvendedor")
                                    .password("123")
                                    .name("Pedro vendedor")
                                    .email("pedro@vendedor.com")
                                    .cpf("10289700564")
                                    .birthdate(LocalDate.of(1998, 8, 27))
                                    .role(Role.SELLER)
                                    .createdAt(LocalDateTime.now())
                                    .updatedAt(LocalDateTime.now())
                                    .build())
                            .productClassification(ProductClassificationEntity
                                    .builder()
                                    .id("67a62ced-1846-45f6-bdd4-2d46c0685066")
                                    .value("Facas")
                                    .build())
                            .build())
                        .build()
        );
    }
}
