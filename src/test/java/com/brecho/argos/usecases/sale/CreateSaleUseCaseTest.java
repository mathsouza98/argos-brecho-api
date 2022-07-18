package com.brecho.argos.usecases.sale;

import com.brecho.argos.domain.inventory.usecases.GetInventoryItemUseCase;
import com.brecho.argos.domain.sale.core.exceptions.InvalidSaleException;
import com.brecho.argos.domain.inventory.core.models.InventoryItem;
import com.brecho.argos.domain.sale.core.exceptions.UnavailableItemException;
import com.brecho.argos.domain.sale.core.models.Sale;
import com.brecho.argos.domain.sale.core.ports.CreateSalePort;
import com.brecho.argos.domain.sale.usecases.CreateSaleUseCase;
import com.brecho.argos.domain.user.core.ports.GetUserPort;
import com.brecho.argos.factory.InventoryItemFactory;
import com.brecho.argos.factory.SaleFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.math.BigDecimal;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

class CreateSaleUseCaseTest {

    @InjectMocks
    private CreateSaleUseCase createSaleUseCase;

    @Mock
    private CreateSalePort createSalePort;

    @Mock
    private GetUserPort getUserPort;

    @Mock
    private GetInventoryItemUseCase getInventoryItemUseCase;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("It should create a sale")
    void shouldCreateSale() {
        //given
        Sale sale = SaleFactory.createValidSale();
        Map<String, InventoryItem> availableItems = InventoryItemFactory.createValidAvailableInventoryItemsMap();

        //when
        when(getInventoryItemUseCase.getAvailableInventoryItemsByProductsIds(anyList())).thenReturn(availableItems);
        when(getUserPort.userExists(any())).thenReturn(true);
        when(createSalePort.create(any(Sale.class))).thenAnswer(i -> i.getArguments()[0]);
        Sale createdSale = createSaleUseCase.createSale(sale);

        //then
        assertNotNull(createdSale.getId());
        assertNotNull(createdSale.getCreatedAt());
        assertNotNull(createdSale.getUpdatedAt());
        assertEquals(Sale.Status.WAITING_PAYMENT, createdSale.getStatus());
        assertEquals(BigDecimal.valueOf(136.5), createdSale.getTotalValue());
    }

    @Test
    @DisplayName("It should not create sale because product is unavailable in inventory")
    void shouldThrowUnavailableItemException() {
        //given
        Sale sale = SaleFactory.createValidSale();

        //when
        when(getInventoryItemUseCase.getAvailableInventoryItemsByProductsIds(anyList())).thenThrow(UnavailableItemException.class);
        when(getUserPort.userExists(any())).thenReturn(true);
        when(createSalePort.create(any(Sale.class))).thenAnswer(i -> i.getArguments()[0]);

        //then
        assertThrows(InvalidSaleException.class, () -> createSaleUseCase.createSale(sale));
    }

    @Test
    @DisplayName("It should not create sale because product quantity is insufficient in inventory")
    void shouldThrowInsufficientQuantityItemException() {
        //given
        Sale sale = SaleFactory.createInvalidSaleBySaleItemQuantityBiggerThanAvailableInInventory();
        Map<String, InventoryItem> availableItems = InventoryItemFactory.createValidAvailableInventoryItemsMap();

        //when
        when(getInventoryItemUseCase.getAvailableInventoryItemsByProductsIds(anyList())).thenReturn(availableItems);
        when(getUserPort.userExists(any())).thenReturn(true);
        when(createSalePort.create(any(Sale.class))).thenAnswer(i -> i.getArguments()[0]);

        //then
        assertThrows(InvalidSaleException.class, () -> createSaleUseCase.createSale(sale));
    }

    @Test
    @DisplayName("It should not create sale because seller and buyer cannot the same")
    void shouldThrowBuyerCannotBeSellerException() {
        //given
        Sale sale = SaleFactory.createInvalidSaleByBuyerAndSellerBeingTheSame();
        Map<String, InventoryItem> availableItems = InventoryItemFactory.createValidAvailableInventoryItemsMap();

        //when
        when(getInventoryItemUseCase.getAvailableInventoryItemsByProductsIds(anyList())).thenReturn(availableItems);
        when(getUserPort.userExists(any())).thenReturn(true);
        when(createSalePort.create(any(Sale.class))).thenAnswer(i -> i.getArguments()[0]);

        //then
        assertThrows(InvalidSaleException.class, () -> createSaleUseCase.createSale(sale));
    }

    @Test
    @DisplayName("It should not create sale because buyer is not registered")
    void shouldNotCreateSaleBecauseBuyerIsNotRegistered() {
        //given
        Sale sale = SaleFactory.createInvalidSaleBecauseBuyerIsNotRegistered();
        Map<String, InventoryItem> availableItems = InventoryItemFactory.createValidAvailableInventoryItemsMap();

        //when
        when(getUserPort.userExists(any())).thenReturn(false);
        when(getInventoryItemUseCase.getAvailableInventoryItemsByProductsIds(anyList())).thenReturn(availableItems);
        when(createSalePort.create(any(Sale.class))).thenAnswer(i -> i.getArguments()[0]);

        //then
        assertThrows(InvalidSaleException.class, () -> createSaleUseCase.createSale(sale));
    }
}
