package com.brecho.argos.domain.sale.adapters.persistance.entity;

import com.brecho.argos.domain.sale.core.enums.InventoryItemStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private ProductEntity product;
}
