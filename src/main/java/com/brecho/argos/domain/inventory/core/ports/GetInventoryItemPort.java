package com.brecho.argos.domain.inventory.core.ports;

import com.brecho.argos.domain.inventory.core.models.InventoryItem;

import java.util.List;

public interface GetInventoryItemPort {
    List<InventoryItem> getAvailableInventoryItemsByProductsIds(List<String> productIds);
}
