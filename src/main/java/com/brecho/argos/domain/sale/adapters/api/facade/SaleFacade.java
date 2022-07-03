package com.brecho.argos.domain.sale.adapters.api.facade;

import com.brecho.argos.domain.sale.adapters.api.mapper.SaleApiMapper;
import com.brecho.argos.domain.sale.adapters.api.models.request.SaleRequest;
import com.brecho.argos.domain.sale.adapters.api.models.response.SaleResponse;
import com.brecho.argos.domain.sale.core.models.Sale;
import com.brecho.argos.domain.sale.usecases.CreateSaleUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SaleFacade {
    private final CreateSaleUseCase createSaleUseCase;
    private final SaleApiMapper saleMapper;

    public SaleResponse createSale(SaleRequest saleRequest, String createdBy) {
        Sale sale = saleMapper.fromRequest(saleRequest, createdBy);
        return saleMapper.toResponse(createSaleUseCase.createSale(sale));
    }
}
