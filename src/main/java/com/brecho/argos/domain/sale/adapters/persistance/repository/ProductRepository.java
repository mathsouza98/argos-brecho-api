package com.brecho.argos.domain.sale.adapters.persistance.repository;

import com.brecho.argos.domain.sale.adapters.persistance.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, String> {
}
