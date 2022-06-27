package com.brecho.argos.domain.inventory.usecases;

import com.brecho.argos.domain.inventory.core.models.InventoryItem;
import com.brecho.argos.domain.inventory.core.ports.GetInventoryItemPort;
import com.brecho.argos.domain.sale.core.exceptions.UnavailableItemException;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class GetInventoryItemUseCase {
    private final GetInventoryItemPort getInventoryItemPort;

    public Map<String, InventoryItem> getAvailableInventoryItemsByProductsIds(List<String> productIds) throws UnavailableItemException {
        Map<String, InventoryItem> availableInventoryItems = getInventoryItemPort.getAvailableInventoryItemsByProductsIds(productIds)
                .stream().collect(Collectors.toMap(item -> item.getProduct().getId(), Function.identity()));
        List<String> unavailableItems = productIds.stream().filter(id -> !availableInventoryItems.containsKey(id)).toList();

        if (!unavailableItems.isEmpty())
            throw new UnavailableItemException(unavailableItems);

        return availableInventoryItems;
    }
}
