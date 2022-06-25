package com.brecho.argos.domain.sale.adapters.persistence.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "sales_items")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SaleItemEntity {
    @Id
    @Column(name = "id", length = 36)
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private ProductEntity product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sale_id")
    private SaleEntity sale;

    @Column(name = "amount")
    private int amount;
}
