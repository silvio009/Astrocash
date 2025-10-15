package com.example.Astrocash.models.users;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

public class Senha {
    private final String value;

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public Senha(String value) {
        if (value == null || value.length() < 6) {
            throw new IllegalArgumentException("A senha deve ter no mínimo 6 caracteres.");
        }
        this.value = value;
    }
    @JsonValue
    public String getValue() {
        return value;
    }
    @Override
    public String toString() {
        return value;
    }

    public String getValor() {
        return value;
    }
}
