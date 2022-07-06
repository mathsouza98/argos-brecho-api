package com.brecho.argos.domain.sale.adapters.api.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class JsonMapper {
    private final ObjectMapper mapper = new ObjectMapper()
            .registerModule(new JavaTimeModule());

    public String toJsonString(Object object) {
        try {
            return mapper.writeValueAsString(object);
        } catch(JsonProcessingException e) {
            e.printStackTrace();
        }
        return "";
    }
}
