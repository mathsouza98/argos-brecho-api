package com.brecho.argos.usecases.sale;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import com.brecho.argos.domain.sale.adapters.persistance.entity.InventoryItemEntity;
import com.brecho.argos.domain.sale.adapters.persistance.entity.SaleEntity;
import com.brecho.argos.domain.sale.adapters.persistance.entity.SaleItemEntity;
import com.brecho.argos.domain.sale.adapters.persistance.mapper.*;
import com.brecho.argos.domain.sale.adapters.persistance.repository.InventoryItemRepository;
import com.brecho.argos.domain.sale.adapters.persistance.repository.SaleRepository;
import com.brecho.argos.domain.sale.core.models.Sale;
import com.brecho.argos.domain.sale.usecases.CreateSaleUseCase;
import com.brecho.argos.domain.user.adapters.persistance.mapper.UserMapper;
import com.brecho.argos.domain.user.adapters.persistance.mapper.UserMapperImpl;
import com.brecho.argos.factory.SaleEntityFactory;
import com.brecho.argos.factory.SaleFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mapstruct.factory.Mappers;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
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
        FixtureFactoryLoader.loadTemplates("com.brecho.argos.factory");
    }

    @Test
    @DisplayName("It should create a sale")
    void shouldCreateSale() {
        //given
        Sale sale = Fixture.from(Sale.class).gimme(SaleFactory.VALID_SALE);
        List<InventoryItemEntity> availableItems = Fixture.from(InventoryItemEntity.class).gimme(3, SaleEntityFactory.VALID_INVENTORY_ITEM);
        BigDecimal saleTotalValue = sale.getSaleItems().stream()
                .map(saleItem -> saleItem.getProduct().getPrice().multiply(BigDecimal.valueOf(saleItem.getAmount())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        //when
        when(inventoryItemRepository.findAvailableInventoryItemsByProductsIds(anyList())).thenReturn(availableItems);
        when(saleRepository.save(any(SaleEntity.class))).thenAnswer(i -> i.getArguments()[0]);

        Sale createdSale = createSaleUseCase.createSale(sale);

        assertNotNull(createdSale.getId());
        assertEquals(Sale.Status.WAITING_PAYMENT, createdSale.getStatus());
        assertEquals(createdSale.getTotalValue(), saleTotalValue);

    }
}
