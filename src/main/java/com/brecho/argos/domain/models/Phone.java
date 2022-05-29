package com.brecho.argos.domain.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Phone {
    private String id;
    private String regionCode;
    private String value;
    private Person person;
}
