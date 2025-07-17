package com.example.Astrocash.models.transaction;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum TipoAtivo {
    CRYPTO,
    STOCK;

    @JsonCreator
    public static TipoAtivo fromString(String value) {
        if (value == null) return null;
        try {
            return TipoAtivo.valueOf(value.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Valor inválido para TipoAtivo: " + value);
        }
    }

    @JsonValue
    public String toJson() {
        return this.name();
    }
}