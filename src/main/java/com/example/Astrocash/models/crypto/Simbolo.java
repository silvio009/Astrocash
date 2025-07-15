package com.example.Astrocash.models.crypto;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.Objects;

public class Simbolo {

    private final SimboloEnum value;

    @JsonCreator
    public Simbolo(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException("Símbolo não pode ser nulo ou vazio.");
        }

        String upper = input.trim().toUpperCase();

        if (upper.length() > 3) {
            throw new IllegalArgumentException("Símbolo deve ter no máximo 3 caracteres.");
        }

        try {
            this.value = SimboloEnum.valueOf(upper);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Símbolo inválido: " + input + ". Valores válidos: BTC, ETH, ADA, DOT ...");
        }
    }

    @JsonValue
    public String getValue() {
        return value.name();
    }

    @Override
    public String toString() {
        return value.name();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Simbolo)) return false;
        Simbolo simbolo = (Simbolo) o;
        return value == simbolo.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}

