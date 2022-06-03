package com.brecho.argos.domain.sale.adapters.persistance.repository;

import com.brecho.argos.domain.sale.adapters.persistance.entity.SaleItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleItemRepository extends JpaRepository<SaleItemEntity, String> {
}
