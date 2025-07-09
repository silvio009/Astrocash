package com.example.Astrocash.models.users;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.*;

import java.util.regex.Pattern;

public class Email {

    private final String value;

    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$"
    );

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public Email(String value) {
        if (value == null || !EMAIL_PATTERN.matcher(value).matches()) {
            throw new IllegalArgumentException("Email inválido.");
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
}