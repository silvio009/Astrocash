package com.example.Astrocash.models.stocks;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.EqualsAndHashCode;

import java.util.Arrays;
import java.util.Set;

@EqualsAndHashCode
public class Mercado {

    private final BolsaDeValores value;


    public Mercado(BolsaDeValores value) {
        this.value = value;
    }

    @JsonCreator // Para o Jackson (quando vem JSON como String)
    public static Mercado fromString(String input) {
        BolsaDeValores enumValue = Arrays.stream(BolsaDeValores.values())
                .filter(b -> b.name().equalsIgnoreCase(input))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        "Mercado inválido. Valores válidos: " + Arrays.toString(BolsaDeValores.values())));
        return new Mercado(enumValue);
    }

    @JsonValue
    public String getValue() {
        return value.name();
    }

    public String getPais() {
        return value.getPais();
    }

    @Override
    public String toString() {
        return value.name();
    }
}