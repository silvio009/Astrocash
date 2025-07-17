package com.example.Astrocash.dto.transaction;

import com.example.Astrocash.models.transaction.TipoAtivo;
import com.example.Astrocash.models.transaction.TipoOperacao;

import java.time.Instant;
import java.time.LocalDateTime;

public record UpadateTransactionDto(TipoAtivo tipoAtivo, Double quantidade, Double precoUnitario, TipoOperacao tipoOperacao, LocalDateTime dataHora) {
}
