package com.brecho.argos.domain.sale.usecases;

import com.brecho.argos.domain.sale.adapters.persistence.mapper.InventoryItemMapper;
import com.brecho.argos.domain.sale.adapters.persistence.mapper.SaleMapper;
import com.brecho.argos.domain.sale.adapters.persistence.repository.InventoryItemRepository;
import com.brecho.argos.domain.sale.adapters.persistence.repository.SaleRepository;
import com.brecho.argos.domain.sale.core.exceptions.BuyerCannotBeSellerException;
import com.brecho.argos.domain.sale.core.exceptions.InsufficientQuantityItemException;
import com.brecho.argos.domain.sale.core.exceptions.InvalidSaleException;
import com.brecho.argos.domain.sale.core.exceptions.UnavailableItemException;
import com.brecho.argos.domain.sale.core.models.InventoryItem;
import com.brecho.argos.domain.sale.core.models.Sale;
import com.brecho.argos.domain.sale.core.models.SaleItem;
import com.brecho.argos.domain.user.core.models.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CreateSaleUseCase {
    private final SaleRepository saleRepository;
    private final InventoryItemRepository inventoryItemRepository;
    private final SaleMapper saleMapper;
    private final InventoryItemMapper inventoryItemMapper;

    public Sale createSale(Sale sale) {
        BigDecimal saleTotalValue = BigDecimal.valueOf(0);
        List<SaleItem> saleItems = sale.getSaleItems();
        List<String> productsIds = saleItems.stream().map(saleItem -> saleItem.getProduct().getId()).toList();
        Map<String, InventoryItem> availableInventoryItems = inventoryItemMapper.toDomainList(inventoryItemRepository.findAvailableInventoryItemsByProductsIds(productsIds))
                .stream().collect(Collectors.toMap(item -> item.getProduct().getId(), Function.identity()));

        try {
            for (SaleItem saleItem : saleItems) {
                String saleItemProductKey = saleItem.getProduct().getId();

                if (!availableInventoryItems.containsKey(saleItemProductKey))
                    throw new UnavailableItemException(saleItem.getProduct());

                InventoryItem inventoryItem = availableInventoryItems.get(saleItemProductKey);
                checkIfSaleItemAmountISAvailableInInventory(saleItem, inventoryItem);
                saleTotalValue = saleTotalValue.add(inventoryItem.getProduct().getPrice().multiply(BigDecimal.valueOf(saleItem.getAmount())));
                checkIfBuyerAndSellerAreNotTheSame(sale.getBuyer(), saleItem.getProduct().getSeller());
            }
        } catch (InsufficientQuantityItemException | BuyerCannotBeSellerException | UnavailableItemException e) {
            throw new InvalidSaleException(e);
        }

        sale.setCreatedAt(LocalDateTime.now());
        sale.setUpdatedAt(LocalDateTime.now());
        sale.setStatus(Sale.Status.WAITING_PAYMENT);
        sale.setTotalValue(saleTotalValue);

        return saleMapper.toDomain(saleRepository.save(saleMapper.toEntity(sale)));
    }

    private void checkIfBuyerAndSellerAreNotTheSame(User buyer, User seller) throws BuyerCannotBeSellerException {
        if (Objects.equals(buyer.getId(), seller.getId()))
            throw new BuyerCannotBeSellerException();
    }

    private void checkIfSaleItemAmountISAvailableInInventory(SaleItem saleItem, InventoryItem inventoryItem) throws InsufficientQuantityItemException {
        if (saleItem.getAmount() > inventoryItem.getAmount())
            throw new InsufficientQuantityItemException(saleItem);
    }
}
