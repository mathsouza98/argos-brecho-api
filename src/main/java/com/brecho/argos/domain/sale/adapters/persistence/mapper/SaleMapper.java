package com.brecho.argos.domain.sale.adapters.persistence.mapper;

import com.brecho.argos.domain.sale.adapters.persistence.entity.SaleEntity;
import com.brecho.argos.domain.sale.core.models.Sale;
import com.brecho.argos.domain.user.adapters.persistance.mapper.UserMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {SaleItemMapper.class, UserMapper.class})
public interface SaleMapper {

    SaleEntity toEntity(Sale sale);

    Sale toDomain(SaleEntity saleEntity);
}
