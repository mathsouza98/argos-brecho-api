package com.brecho.argos.domain.sale.core.ports;

import com.brecho.argos.domain.sale.core.models.InventoryItem;

import java.util.List;

public interface GetInventoryItemPort {
    List<InventoryItem> getAvailableInventoryItemsByProductsIds(List<String> productIds);
}
