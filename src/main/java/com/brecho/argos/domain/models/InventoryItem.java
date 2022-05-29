package com.brecho.argos.domain.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InventoryItem {
    private String id;
    private int status;
    private int amount;
    private Product product;
}
