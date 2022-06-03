package com.brecho.argos.domain.sale.adapters.persistance.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "products_classification")
@Getter
@Setter
@NoArgsConstructor
public class ProductClassificationEntity {
    @Id
    @Column(name = "id", length = 36)
    private String id;

    @Column(name = "value", length = 50)
    private String value;
}
