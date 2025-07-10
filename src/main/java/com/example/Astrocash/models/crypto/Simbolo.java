package com.example.Astrocash.models.crypto;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.Objects;

public class Simbolo {

    private final String value;

    @JsonCreator
    public Simbolo(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Símbolo não pode ser nulo ou vazio.");
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Simbolo)) return false;
        Simbolo simbolo = (Simbolo) o;
        return Objects.equals(value, simbolo.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    // ARRUMAR O ESCAPSULAMENTO DO SIMBOLO COM UM ENUM E DEPOIS FAZER O SERVICE COM OS DADOS UNICOS E ETC
}