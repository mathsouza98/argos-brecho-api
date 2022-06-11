package com.brecho.argos.domain.sale.adapters.persistance.mapper;

import com.brecho.argos.domain.sale.adapters.persistance.entity.ProductEntity;
import com.brecho.argos.domain.sale.core.models.Product;
import com.brecho.argos.domain.user.adapters.persistance.mapper.UserMapper;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface ProductMapper {

    ProductEntity toEntity(Product product);

    Product toDomain(ProductEntity product);
}
