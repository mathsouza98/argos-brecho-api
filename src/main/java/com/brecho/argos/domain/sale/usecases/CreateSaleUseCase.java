package com.brecho.argos.domain.sale.usecases;

import com.brecho.argos.domain.inventory.usecases.GetInventoryItemUseCase;
import com.brecho.argos.domain.sale.core.exceptions.*;
import com.brecho.argos.domain.inventory.core.models.InventoryItem;
import com.brecho.argos.domain.sale.core.models.Sale;
import com.brecho.argos.domain.sale.core.models.SaleItem;
import com.brecho.argos.domain.sale.core.ports.CreateSalePort;
import com.brecho.argos.domain.user.core.models.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CreateSaleUseCase {
    private final CreateSalePort createSalePort;
    private final GetInventoryItemUseCase getInventoryItemUseCase;

    public Sale createSale(Sale sale) {
        BigDecimal saleTotalValue = BigDecimal.valueOf(0);
        List<SaleItem> saleItems = sale.getSaleItems();
        List<String> productsIds = saleItems.stream().map(saleItem -> saleItem.getProduct().getId()).toList();

        try {
            Map<String, InventoryItem> availableInventoryItems = getInventoryItemUseCase.getAvailableInventoryItemsByProductsIds(productsIds);

            for (SaleItem saleItem : saleItems) {
                String saleItemProductKey = saleItem.getProduct().getId();
                InventoryItem inventoryItem = availableInventoryItems.get(saleItemProductKey);
                checkIfSaleItemAmountISAvailableInInventory(saleItem, inventoryItem);
                saleTotalValue = saleTotalValue.add(inventoryItem.getProduct().getPrice().multiply(BigDecimal.valueOf(saleItem.getAmount())));
                checkIfBuyerAndSellerAreNotTheSame(sale.getBuyer(), saleItem.getProduct().getSeller());
            }
        } catch (InsufficientQuantityItemException | BuyerCannotBeSellerException | UnavailableItemException |
                 EmptyArgumentsException e) {
            throw new InvalidSaleException(e);
        }

        sale.setCreatedAt(LocalDateTime.now());
        sale.setUpdatedAt(LocalDateTime.now());
        sale.setStatus(Sale.Status.WAITING_PAYMENT);
        sale.setTotalValue(saleTotalValue);

        return createSalePort.create(sale);
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
