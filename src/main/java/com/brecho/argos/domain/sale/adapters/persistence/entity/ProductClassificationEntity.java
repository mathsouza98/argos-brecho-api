package com.brecho.argos.domain.sale.adapters.persistence.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "product_classifications")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductClassificationEntity {
    @Id
    @Column(name = "id", length = 36)
    private String id;

    @Column(name = "value", length = 50)
    private String value;
}
