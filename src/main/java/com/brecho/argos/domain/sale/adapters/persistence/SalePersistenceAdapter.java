package com.brecho.argos.domain.sale.adapters.persistence;

import com.brecho.argos.domain.sale.adapters.persistence.entity.SaleEntity;
import com.brecho.argos.domain.sale.adapters.persistence.mapper.SaleMapper;
import com.brecho.argos.domain.sale.adapters.persistence.repository.SaleRepository;
import com.brecho.argos.domain.sale.core.models.Sale;
import com.brecho.argos.domain.sale.core.ports.CreateSalePort;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public class SalePersistenceAdapter implements CreateSalePort {
    private final SaleRepository saleRepository;
    private final SaleMapper saleMapper;

    @Override
    public Sale create(Sale sale) {
        SaleEntity saleEntity = saleMapper.toEntity(sale);
        saleEntity.setId(UUID.randomUUID().toString());
        return saleMapper.toDomain(saleRepository.save(saleEntity));
    }
}
