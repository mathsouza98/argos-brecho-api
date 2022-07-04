package com.brecho.argos.domain.sale.adapters.persistence.mapper;

import com.brecho.argos.domain.sale.adapters.persistence.entity.ProductEntity;
import com.brecho.argos.domain.sale.core.models.Product;
import com.brecho.argos.domain.user.adapters.persistence.mapper.UserMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface ProductMapper {

    ProductEntity toEntity(Product product);

    Product toDomain(ProductEntity product);
}
