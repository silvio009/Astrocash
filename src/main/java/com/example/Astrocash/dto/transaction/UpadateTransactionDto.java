package com.example.Astrocash.dto.transaction;

import java.time.Instant;
import java.time.LocalDateTime;

public record UpadateTransactionDto(String tipoAtivo, Double quantidade, Double precoUnitario, String tipoOperacao, LocalDateTime dataHora) {
}
