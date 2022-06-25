package com.brecho.argos.factory;

import com.brecho.argos.domain.sale.adapters.persistence.entity.*;
import com.brecho.argos.domain.sale.core.enums.InventoryItemStatus;
import com.brecho.argos.domain.sale.core.models.InventoryItem;
import com.brecho.argos.domain.sale.core.models.Product;
import com.brecho.argos.domain.sale.core.models.ProductClassification;
import com.brecho.argos.domain.user.adapters.persistance.entity.UserEntity;
import com.brecho.argos.domain.user.core.enums.Role;
import com.brecho.argos.domain.user.core.models.User;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class InventoryItemFactory {
    public static List<InventoryItem> createValidAvailableInventoryItems() {
        return List.of(
                InventoryItem
                        .builder()
                        .id("712b595f-b83f-43a7-8858-90d7f6f54aca")
                        .status(InventoryItemStatus.AVAILABLE)
                        .amount(33)
                        .createdAt(LocalDateTime.now())
                        .updatedAt(LocalDateTime.now())
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
                        .build()
        );
    }
}
