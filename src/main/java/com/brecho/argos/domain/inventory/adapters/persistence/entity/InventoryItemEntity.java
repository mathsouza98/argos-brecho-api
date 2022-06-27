package com.brecho.argos.domain.inventory.adapters.persistence.entity;

import com.brecho.argos.domain.sale.adapters.persistence.entity.ProductEntity;
import com.brecho.argos.domain.sale.core.enums.InventoryItemStatus;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "inventory_items")
@Getter
@Setter
@NoArgsConstructor
public class InventoryItemEntity {
    @Id
    @Column(name = "id", length = 36)
    private String id;

    @Enumerated
    @Column(name = "status")
    private InventoryItemStatus status;

    @Column(name = "amount")
    private int amount;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private ProductEntity product;
}
