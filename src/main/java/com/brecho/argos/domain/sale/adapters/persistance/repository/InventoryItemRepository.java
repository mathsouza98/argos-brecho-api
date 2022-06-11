package com.brecho.argos.domain.sale.adapters.persistance.repository;

import com.brecho.argos.domain.sale.adapters.persistance.entity.InventoryItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InventoryItemRepository extends JpaRepository<InventoryItemEntity, String> {

    @Query("SELECT * FROM InventoryItemEntity WHERE product_id IN (:ids) AND status = 1")
    List<InventoryItemEntity> findAvailableInventoryItemsByProductsIds(@Param("ids") List<String> ids);
}
