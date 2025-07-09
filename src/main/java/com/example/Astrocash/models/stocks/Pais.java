package com.example.Astrocash.models.stocks;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class Pais {
    private final PaisEnum value;

    @JsonCreator
    public static Pais fromString(String sigla) {
        PaisEnum paisEnum = PaisEnum.fromSigla(sigla);
        return new Pais(paisEnum);
    }

    public Pais(PaisEnum value) {
        if (value == null) {
            throw new IllegalArgumentException("País não pode ser nulo.");
        }
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value.getSigla();
    }

    @Override
    public String toString() {
        return value.name();
    }
}