package com.brecho.argos.adapters.api.controller;

import com.brecho.argos.domain.sale.adapters.api.controller.SaleController;
import com.brecho.argos.domain.sale.adapters.api.facade.SaleFacade;
import com.brecho.argos.domain.sale.adapters.api.models.request.SaleRequest;
import com.brecho.argos.domain.sale.adapters.api.models.response.SaleResponse;
import com.brecho.argos.factory.api.request.SaleRequestFactory;
import com.brecho.argos.factory.api.response.SaleResponseFactory;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RequiredArgsConstructor
class SaleControllerTest {
    @InjectMocks
    private SaleController saleController;

    @Mock
    private SaleFacade saleFacade;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("It should return 201 when create sale")
    void shouldReturn201OnCreateSale() {
        //given
        SaleRequest saleRequest = SaleRequestFactory.createValidSaleRequest();
        String buyerId = SaleRequestFactory.createValidBuyerId();
        SaleResponse saleResponse = SaleResponseFactory.createValidSaleResponse();

        //when
        when(saleFacade.createSale(any(), any())).thenReturn(saleResponse);
        ResponseEntity<SaleResponse> saleResponseEntity = saleController.createSale(saleRequest, buyerId);

        //then
        assertEquals(HttpStatus.CREATED, saleResponseEntity.getStatusCode());
        assertEquals(saleResponse, saleResponseEntity.getBody());
    }
}
