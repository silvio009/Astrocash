package com.example.Astrocash.models.transaction;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum TipoOperacao {
    COMPRA,
    VENDA;

    @JsonCreator
    public static TipoOperacao fromString(String value) {
        if (value == null) return null;
        try {
            return TipoOperacao.valueOf(value.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Valor inválido para TipoOperacao: " + value);
        }
    }

    @JsonValue
    public String toJson() {
        return this.name();
    }
}