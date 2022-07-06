package com.brecho.argos.adapters.api.controller;

import com.brecho.argos.domain.sale.adapters.api.controller.SaleController;
import com.brecho.argos.domain.sale.adapters.api.facade.SaleFacade;
import com.brecho.argos.domain.sale.adapters.api.interceptors.ControllerErrorAdvice;
import com.brecho.argos.domain.sale.adapters.api.mapper.JsonMapper;
import com.brecho.argos.domain.sale.adapters.api.models.request.SaleRequest;
import com.brecho.argos.domain.sale.adapters.api.models.response.SaleResponse;
import com.brecho.argos.domain.sale.core.exceptions.BuyerCannotBeSellerException;
import com.brecho.argos.domain.sale.core.exceptions.EmptyArgumentsException;
import com.brecho.argos.domain.sale.core.exceptions.InsufficientQuantityItemException;
import com.brecho.argos.domain.sale.core.exceptions.UnavailableItemException;
import com.brecho.argos.domain.user.core.UserNotFoundException;
import com.brecho.argos.factory.api.request.SaleRequestFactory;
import com.brecho.argos.factory.api.response.SaleResponseFactory;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RequiredArgsConstructor
class SaleControllerTest {
    @InjectMocks
    private SaleController saleController;

    @Mock
    private SaleFacade saleFacade;

    private MockMvc mockMvc;

    @Spy
    private JsonMapper jsonMapper;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(saleController)
                .setControllerAdvice(new ControllerErrorAdvice()).build();
    }

    @Test
    @DisplayName("It should return 201 when create sale")
    void shouldCreateSaleAndReturn201() throws Exception {
        //given
        SaleResponse saleResponse = SaleResponseFactory.createValidSaleResponse();

        //when
        when(saleFacade.createSale(any(), any())).thenReturn(saleResponse);

        //then
        mockMvc.perform(post("/v1/sale"))
                .andExpect(status().isCreated())
                .andExpect(result -> assertEquals(jsonMapper.toJsonString(saleResponse), result.getResponse().getContentAsString()));
    }

    @Test
    @DisplayName("It should return 404 because UnavailableItemException is thrown")
    void shouldThrowUnavailableItemExceptionAndReturn404() throws Exception {
        //given
        SaleRequest saleRequest = SaleRequestFactory.createValidSaleRequest();
        String buyerId = SaleRequestFactory.createValidBuyerId();

        //when
        when(saleFacade.createSale(any(), any())).thenThrow(UnavailableItemException.class);
        mockMvc.perform(post("/v1/sale", saleRequest, buyerId))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("It should return 422 because InsufficientQuantityItemException is thrown")
    void shouldThrowInsufficientQuantityItemExceptionAndReturn422() throws Exception {
        //given
        SaleRequest saleRequest = SaleRequestFactory.createValidSaleRequest();
        String buyerId = SaleRequestFactory.createValidBuyerId();

        //when
        when(saleFacade.createSale(any(), any())).thenThrow(InsufficientQuantityItemException.class);
        mockMvc.perform(post("/v1/sale", saleRequest, buyerId))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    @DisplayName("It should return 404 because UserNotFoundException is thrown")
    void shouldThrowUserNotFoundExceptionAndReturn422() throws Exception {
        //given
        SaleRequest saleRequest = SaleRequestFactory.createValidSaleRequest();
        String buyerId = SaleRequestFactory.createValidBuyerId();

        //when
        when(saleFacade.createSale(any(), any())).thenThrow(UserNotFoundException.class);
        mockMvc.perform(post("/v1/sale", saleRequest, buyerId))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("It should return 400 because BuyerCannotBeSellerException is thrown")
    void shouldThrowBuyerCannotBeSellerExceptionAndReturn422() throws Exception {
        //given
        SaleRequest saleRequest = SaleRequestFactory.createValidSaleRequest();
        String buyerId = SaleRequestFactory.createValidBuyerId();

        //when
        when(saleFacade.createSale(any(), any())).thenThrow(BuyerCannotBeSellerException.class);
        mockMvc.perform(post("/v1/sale", saleRequest, buyerId))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("It should return 400 because EmptyArgumentsException is thrown")
    void shouldThrowEmptyArgumentsExceptionAndReturn422() throws Exception {
        //given
        SaleRequest saleRequest = SaleRequestFactory.createValidSaleRequest();
        String buyerId = SaleRequestFactory.createValidBuyerId();

        //when
        when(saleFacade.createSale(any(), any())).thenThrow(EmptyArgumentsException.class);
        mockMvc.perform(post("/v1/sale", saleRequest, buyerId))
                .andExpect(status().isBadRequest());
    }
}
