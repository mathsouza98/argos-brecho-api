package com.brecho.argos.domain.sale.adapters.persistence.repository;

import com.brecho.argos.domain.sale.adapters.persistence.entity.ProductClassificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductClassificationRepository extends JpaRepository<ProductClassificationEntity, String> {
}
