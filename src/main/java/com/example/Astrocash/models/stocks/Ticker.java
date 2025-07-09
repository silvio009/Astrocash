package com.example.Astrocash.models.stocks;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class Ticker {
    private final String value;

    @JsonCreator
    public Ticker(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Ticker não pode ser vazio.");
        }
        this.value = value.toUpperCase();
    }
    @JsonValue
    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }
}