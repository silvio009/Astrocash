package com.example.Astrocash.dto.transaction;

import java.time.Instant;

public record UpadateTransactionDto(String tipoAtivo, Double quantidade, Double precoUnitario, String tipoOperacao, Instant dataHora) {
}
