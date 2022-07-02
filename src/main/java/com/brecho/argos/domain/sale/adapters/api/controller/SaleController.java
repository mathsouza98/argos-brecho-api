package com.brecho.argos.domain.sale.adapters.api.controller;

import com.brecho.argos.domain.sale.adapters.api.facade.SaleFacade;
import com.brecho.argos.domain.sale.adapters.api.models.request.SaleRequest;
import com.brecho.argos.domain.sale.adapters.api.models.response.SaleResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/sale")
@RequiredArgsConstructor
public class SaleController {
    private final SaleFacade saleFacade;

    @PostMapping
    public ResponseEntity<SaleResponse> createSale(SaleRequest saleRequest, @Param("createdBy") String createdBy) {
        return new ResponseEntity<>(saleFacade.createSale(saleRequest, createdBy), HttpStatus.CREATED);
    }

}
