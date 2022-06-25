package com.brecho.argos.usecases.sale;

import com.brecho.argos.domain.sale.adapters.persistence.entity.InventoryItemEntity;
import com.brecho.argos.domain.sale.adapters.persistence.entity.SaleEntity;
import com.brecho.argos.domain.sale.adapters.persistence.mapper.*;
import com.brecho.argos.domain.sale.adapters.persistence.repository.InventoryItemRepository;
import com.brecho.argos.domain.sale.adapters.persistence.repository.SaleRepository;
import com.brecho.argos.domain.sale.core.exceptions.InvalidSaleException;
import com.brecho.argos.domain.sale.core.models.Sale;
import com.brecho.argos.domain.sale.usecases.CreateSaleUseCase;
import com.brecho.argos.domain.user.adapters.persistance.mapper.UserMapper;
import com.brecho.argos.domain.user.adapters.persistance.mapper.UserMapperImpl;
import com.brecho.argos.factory.SaleEntityFactory;
import com.brecho.argos.factory.SaleFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

class CreateSaleUseCaseTest {

    @InjectMocks
    private CreateSaleUseCase createSaleUseCase;

    @Mock
    private SaleRepository saleRepository;

    @Mock
    private InventoryItemRepository inventoryItemRepository;

    @Spy
    @InjectMocks
    private InventoryItemMapper inventoryItemMapper = Mockito.spy(new InventoryItemMapperImpl());

    @Spy
    @InjectMocks
    private ProductMapper productMapper = Mockito.spy(new ProductMapperImpl());

    @Spy
    private UserMapper userMapper = Mockito.spy(new UserMapperImpl());

    @Spy
    @InjectMocks
    private SaleMapper saleMapper = Mockito.spy(new SaleMapperImpl());

    @Spy
    @InjectMocks
    private SaleItemMapper saleItemMapper = Mockito.spy(new SaleItemMapperImpl());

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("It should create a sale")
    void shouldCreateSale() {
        //given
        Sale sale = SaleFactory.createValidSale();
        List<InventoryItemEntity> availableItems = SaleEntityFactory.createValidAvailableInventoryItems();

        //when
        when(inventoryItemRepository.getAvailableInventoryItemsByProductsIds(anyList())).thenReturn(availableItems);
        when(saleRepository.save(any(SaleEntity.class))).thenAnswer(i -> i.getArguments()[0]);
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
        List<InventoryItemEntity> availableItems = new ArrayList<>();

        //when
        when(inventoryItemRepository.getAvailableInventoryItemsByProductsIds(anyList())).thenReturn(availableItems);
        when(saleRepository.save(any(SaleEntity.class))).thenAnswer(i -> i.getArguments()[0]);

        //then
        assertThrows(InvalidSaleException.class, () -> createSaleUseCase.createSale(sale));
    }

    @Test
    @DisplayName("It should not create sale because product quantity is insufficient in inventory")
    void shouldThrowInsufficientQuantityItemException() {
        //given
        Sale sale = SaleFactory.createInvalidSaleBySaleItemQuantityBiggerThanAvailableInInventory();
        List<InventoryItemEntity> availableItems = SaleEntityFactory.createValidAvailableInventoryItems();

        //when
        when(inventoryItemRepository.getAvailableInventoryItemsByProductsIds(anyList())).thenReturn(availableItems);
        when(saleRepository.save(any(SaleEntity.class))).thenAnswer(i -> i.getArguments()[0]);

        //then
        assertThrows(InvalidSaleException.class, () -> createSaleUseCase.createSale(sale));
    }

    @Test
    @DisplayName("It should not create sale because seller and buyer cannot the same")
    void shouldThrowBuyerCannotBeSellerException() {
        //given
        Sale sale = SaleFactory.createInvalidSaleByBuyerAndSellerBeingTheSame();
        List<InventoryItemEntity> availableItems = SaleEntityFactory.createValidAvailableInventoryItems();

        //when
        when(inventoryItemRepository.getAvailableInventoryItemsByProductsIds(anyList())).thenReturn(availableItems);
        when(saleRepository.save(any(SaleEntity.class))).thenAnswer(i -> i.getArguments()[0]);

        //then
        assertThrows(InvalidSaleException.class, () -> createSaleUseCase.createSale(sale));
    }
}
