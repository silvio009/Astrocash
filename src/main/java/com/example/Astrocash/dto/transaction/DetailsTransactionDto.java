package com.example.Astrocash.dto.transaction;

import com.example.Astrocash.models.Transaction;

import java.time.Instant;

public record DetailsTransactionDto(String id, String userId, String ativoId, String tipoAtivo, Double quantidade, Double precoUnitario, String tipoOperacao, Instant dataHora) {
    public DetailsTransactionDto(Transaction transaction){
        this(transaction.getId(), transaction.getUserId(), transaction.getAtivoId(), transaction.getTipoAtivo(), transaction.getQuantidade(), transaction.getPrecoUnitario(), transaction.getTipoOperacao(), transaction.getDataHora());
    }
}
