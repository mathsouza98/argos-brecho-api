package com.brecho.argos.domain.sale.adapters.persistence.mapper;

import com.brecho.argos.domain.sale.adapters.persistence.entity.InventoryItemEntity;
import com.brecho.argos.domain.sale.core.models.InventoryItem;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ProductMapper.class})
public interface InventoryItemMapper {

    InventoryItemEntity toEntity(InventoryItem inventoryItem);

    InventoryItem toDomain(InventoryItemEntity inventoryItemEntity);

    List<InventoryItemEntity> toEntityList(List<InventoryItem> inventoryItems);

    List<InventoryItem> toDomainList(List<InventoryItemEntity> inventoryItemEntities);
}
