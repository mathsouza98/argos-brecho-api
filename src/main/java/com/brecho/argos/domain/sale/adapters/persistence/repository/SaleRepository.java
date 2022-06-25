package com.brecho.argos.domain.sale.adapters.persistence.repository;

import com.brecho.argos.domain.sale.adapters.persistence.entity.SaleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleRepository extends JpaRepository<SaleEntity, String> {
}
