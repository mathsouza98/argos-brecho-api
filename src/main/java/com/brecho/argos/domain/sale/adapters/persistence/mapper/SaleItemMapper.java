package com.brecho.argos.domain.sale.adapters.persistence.mapper;

import com.brecho.argos.domain.sale.adapters.persistence.entity.SaleItemEntity;
import com.brecho.argos.domain.sale.core.models.SaleItem;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {ProductMapper.class})
public interface SaleItemMapper {

    SaleItemEntity toEntity(SaleItem saleItem);

    SaleItem toDomain(SaleItemEntity saleEntity);
}
