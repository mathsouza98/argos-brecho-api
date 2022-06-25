package com.brecho.argos.domain.sale.adapters.persistence.entity;

import com.brecho.argos.domain.sale.core.models.Sale;
import com.brecho.argos.domain.user.adapters.persistance.entity.UserEntity;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

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
    @JoinColumn(name = "buyer_id")
    private UserEntity buyer;

    @OneToMany(cascade = CascadeType.ALL)
    private List<SaleItemEntity> saleItems;
}
