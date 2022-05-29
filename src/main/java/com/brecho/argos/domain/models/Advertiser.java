package com.brecho.argos.domain.models;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Advertiser {
    private String id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Person person;
}
