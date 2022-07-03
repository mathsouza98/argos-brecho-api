package com.brecho.argos.domain.sale.adapters.api.mapper;

import com.brecho.argos.domain.sale.adapters.api.models.request.SaleRequest;
import com.brecho.argos.domain.sale.adapters.api.models.response.SaleResponse;
import com.brecho.argos.domain.sale.core.models.Sale;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SaleApiMapper {

    @Mapping(source = "createdBy", target = "buyer.id")
    Sale fromRequest(SaleRequest saleRequest, String createdBy);

    SaleResponse toResponse(Sale sale);
}
