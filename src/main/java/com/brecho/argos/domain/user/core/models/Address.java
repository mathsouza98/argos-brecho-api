package com.brecho.argos.domain.user.core.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Address {
    private String id;
    private String stateCode;
    private String streetName;
    private int number;
    private String zipcode;
    private String complement;
    private User person;
}
