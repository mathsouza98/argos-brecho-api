package com.brecho.argos.domain.sale.core.ports;

import com.brecho.argos.domain.sale.core.models.Sale;

public interface CreateSalePort {
    Sale create(Sale sale);
}
