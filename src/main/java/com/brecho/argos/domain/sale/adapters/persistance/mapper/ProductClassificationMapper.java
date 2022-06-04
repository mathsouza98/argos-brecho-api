package com.brecho.argos.domain.sale.adapters.persistance.mapper;

import com.brecho.argos.domain.sale.adapters.persistance.entity.ProductClassificationEntity;
import com.brecho.argos.domain.sale.core.models.ProductClassification;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductClassificationMapper {

    ProductClassificationEntity toEntity(ProductClassification productClassification);

    ProductClassification toDomain(ProductClassificationEntity productClassificationEntity);
}
