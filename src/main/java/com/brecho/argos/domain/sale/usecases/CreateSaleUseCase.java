package com.brecho.argos.domain.sale.usecases;

import com.brecho.argos.domain.sale.adapters.persistance.mapper.InventoryItemMapper;
import com.brecho.argos.domain.sale.adapters.persistance.mapper.SaleMapper;
import com.brecho.argos.domain.sale.adapters.persistance.repository.InventoryItemRepository;
import com.brecho.argos.domain.sale.adapters.persistance.repository.SaleRepository;
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
//TODO: Enhance exception using dedicated ones
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

        for (SaleItem saleItem : saleItems) {
            String saleItemProductKey = saleItem.getProduct().getId();

            if (availableInventoryItems.containsKey(saleItemProductKey)) {
                InventoryItem inventoryItem = availableInventoryItems.get(saleItemProductKey);
                checkIfSaleItemAmountISAvailableInInventory(saleItem, inventoryItem);
                saleTotalValue = saleTotalValue.add(saleItem.getProduct().getPrice().multiply(BigDecimal.valueOf(saleItem.getAmount())));
                checkIfBuyerAndSellerAreNotTheSame(sale.getBuyer(), saleItem.getProduct().getSeller());
            } else {
                throw new RuntimeException("Produto indisponível em estoque, Product={%s}".formatted(saleItem.getProduct()));
            }
        }

        sale.setCreatedAt(LocalDateTime.now());
        sale.setUpdatedAt(LocalDateTime.now());
        sale.setStatus(Sale.Status.WAITING_PAYMENT);
        sale.setTotalValue(saleTotalValue);

        return saleMapper.toDomain(saleRepository.save(saleMapper.toEntity(sale)));
    }

    private void checkIfBuyerAndSellerAreNotTheSame(User buyer, User seller) throws RuntimeException {
        if (Objects.equals(buyer.getId(), seller.getId()))
            throw new RuntimeException("Usuário não pode comprar um produto que ele mesmo anúnciou");
    }

    private void checkIfSaleItemAmountISAvailableInInventory(SaleItem saleItem, InventoryItem inventoryItem) throws RuntimeException {
        if (saleItem.getAmount() > inventoryItem.getAmount())
            throw new RuntimeException("Quantidade não disponível em estoque para o item {%s}".formatted(saleItem));
    }
}
