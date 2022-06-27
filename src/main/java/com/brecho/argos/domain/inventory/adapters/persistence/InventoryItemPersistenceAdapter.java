package com.brecho.argos.domain.inventory.adapters.persistence;

import com.brecho.argos.domain.inventory.adapters.persistence.mapper.InventoryItemMapper;
import com.brecho.argos.domain.inventory.adapters.persistence.repository.InventoryItemRepository;
import com.brecho.argos.domain.inventory.core.models.InventoryItem;
import com.brecho.argos.domain.inventory.core.ports.GetInventoryItemPort;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class InventoryItemPersistenceAdapter implements GetInventoryItemPort {
    private final InventoryItemRepository inventoryItemRepository;
    private final InventoryItemMapper inventoryItemMapper;

    @Override
    public List<InventoryItem> getAvailableInventoryItemsByProductsIds(List<String> productIds) {
        return inventoryItemMapper.toDomainList(inventoryItemRepository.getAvailableInventoryItemsByProductsIds(productIds));
    }
}
