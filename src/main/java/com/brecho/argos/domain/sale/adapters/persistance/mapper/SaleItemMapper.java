package com.brecho.argos.domain.sale.adapters.persistance.mapper;

import com.brecho.argos.domain.sale.adapters.persistance.entity.SaleItemEntity;
import com.brecho.argos.domain.sale.core.models.SaleItem;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {ProductMapper.class})
public interface SaleItemMapper {

    SaleItemEntity toEntity(SaleItem saleItem);

    SaleItem toDomain(SaleItemEntity saleEntity);
}
