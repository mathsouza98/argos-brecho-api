package com.brecho.argos.domain.sale.adapters.persistance.entity;

import com.brecho.argos.domain.user.adapters.persistance.entity.UserEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
public class ProductEntity {
    @Id
    @Column(name = "id", length = 36)
    private String id;

    @Column(name = "name", length = 50)
    private String name;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @JoinColumn(name = "seller_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private UserEntity seller;

    @JoinColumn(name = "product_classification_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private ProductClassificationEntity productClassification;
}
