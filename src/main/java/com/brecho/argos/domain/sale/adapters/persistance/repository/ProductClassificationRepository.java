package com.brecho.argos.domain.sale.adapters.persistance.repository;

import com.brecho.argos.domain.sale.adapters.persistance.entity.ProductClassificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductClassificationRepository extends JpaRepository<ProductClassificationEntity, String> {
}
