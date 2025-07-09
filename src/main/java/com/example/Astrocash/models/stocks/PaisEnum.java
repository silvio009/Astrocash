package com.example.Astrocash.models.stocks;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

public enum PaisEnum {
    BR("BR", "Brasil"),
    US("US", "EUA", "Estados Unidos"),
    MX("MX", "México"),
    CA("CA", "Canadá"),
    AR("AR", "Argentina");

    private final String sigla;
    private final String[] apelidos;

    PaisEnum(String sigla, String... apelidos) {
        this.sigla = sigla;
        this.apelidos = apelidos;
    }

    @JsonValue
    public String getSigla() {
        return sigla;
    }

    public String[] getApelidos() {
        return apelidos;
    }

    @JsonCreator
    public static PaisEnum fromSigla(String input) {
        if (input == null) throw new IllegalArgumentException("País não pode ser nulo.");

        String normalized = input.trim().toLowerCase();

        for (PaisEnum pais : values()) {
            if (pais.sigla.equalsIgnoreCase(normalized)) return pais;
            for (String alias : pais.apelidos) {
                if (alias.equalsIgnoreCase(normalized)) return pais;
            }
        }

        throw new IllegalArgumentException("País inválido: " + input + ". Válidos: BR, US, MX, CA, AR");
    }

    @Override
    public String toString() {
        return sigla;
    }
}
