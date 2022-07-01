package com.brecho.argos.usecases.inventory;

import com.brecho.argos.domain.inventory.core.models.InventoryItem;
import com.brecho.argos.domain.inventory.core.ports.GetInventoryItemPort;
import com.brecho.argos.domain.inventory.usecases.GetInventoryItemUseCase;
import com.brecho.argos.domain.sale.core.exceptions.EmptyArgumentsException;
import com.brecho.argos.domain.sale.core.exceptions.UnavailableItemException;
import com.brecho.argos.factory.InventoryItemFactory;
import com.brecho.argos.factory.SaleFactory;
import com.brecho.argos.factory.entity.InventoryItemEntityFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

class GetInventoryItemUseCaseTest {
    @InjectMocks
    private GetInventoryItemUseCase getInventoryItemUseCase;

    @Mock
    private GetInventoryItemPort getInventoryItemPort;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("It should get available inventory items by product ids")
    void shouldGetAvailableInventoryItemsByProductsIds() {
        //given
        List<InventoryItem> availableItems = InventoryItemFactory.createValidAvailableInventoryItemsList();
        List<String> productIds = InventoryItemEntityFactory.createValidProductIds();

        //when
        when(getInventoryItemPort.getAvailableInventoryItemsByProductsIds(anyList())).thenReturn(availableItems);
        Map<String, InventoryItem> availableItemsMap = getInventoryItemUseCase.getAvailableInventoryItemsByProductsIds(productIds);

        //then
        assertTrue(availableItemsMap.containsKey(availableItems.get(0).getProduct().getId()));
        assertEquals(availableItems.get(0), availableItemsMap.get(availableItems.get(0).getProduct().getId()));
    }

    @Test
    @DisplayName("It should not get available inventory items because product ids param is empty")
    void shouldNotGetAvailableInventoryItemsBecauseProductIdsIsEmpty() {
        //given
        List<InventoryItem> availableItems = new ArrayList<>();
        List<String> productIds = new ArrayList<>();

        //when
        when(getInventoryItemPort.getAvailableInventoryItemsByProductsIds(anyList())).thenReturn(availableItems);

        //then
        assertThrows(EmptyArgumentsException.class, () -> getInventoryItemUseCase.getAvailableInventoryItemsByProductsIds(productIds));
    }

    @Test
    @DisplayName("It should not get available inventory items because items are unavailable")
    void shouldNotGetAvailableInventoryItemsBecauseTheyAreUnavailable() {
        //given
        List<InventoryItem> availableItems = new ArrayList<>();
        List<String> productIds = SaleFactory.createUnavailableProductIds();

        //when
        when(getInventoryItemPort.getAvailableInventoryItemsByProductsIds(anyList())).thenReturn(availableItems);

        //then
        assertThrows(UnavailableItemException.class, () -> getInventoryItemUseCase.getAvailableInventoryItemsByProductsIds(productIds));
    }
}
