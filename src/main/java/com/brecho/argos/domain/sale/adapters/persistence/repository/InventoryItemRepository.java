package com.brecho.argos.domain.sale.adapters.persistence.repository;

import com.brecho.argos.domain.sale.adapters.persistence.entity.InventoryItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryItemRepository extends JpaRepository<InventoryItemEntity, String> {

    @Query("SELECT * FROM InventoryItemEntity WHERE product_id IN (:ids) AND status = 1")
    List<InventoryItemEntity> getAvailableInventoryItemsByProductsIds(@Param("ids") List<String> ids);
}
