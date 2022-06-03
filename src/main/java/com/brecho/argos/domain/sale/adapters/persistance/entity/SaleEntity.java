package com.brecho.argos.domain.sale.adapters.persistance.entity;

import com.brecho.argos.domain.sale.core.models.Sale;
import com.brecho.argos.domain.user.adapters.persistance.entity.UserEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "sales")
@Getter
@Setter
@NoArgsConstructor
public class SaleEntity {
    @Id
    @Column(name = "id", length = 36)
    private String id;

    @Enumerated
    @Column(name = "status")
    private Sale.Status status;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "total_value")
    private BigDecimal totalValue;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private UserEntity customer;
}
