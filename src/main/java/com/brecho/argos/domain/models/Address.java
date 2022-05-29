package com.brecho.argos.domain.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Address {
    private String id;
    private String countryCode;
    private String streetName;
    private String number;
    private String zipcode;
    private String complement;
    private Person person;
}
