package com.example.Astrocash.dto.transaction;

import com.example.Astrocash.models.transaction.TipoAtivo;
import com.example.Astrocash.models.transaction.TipoOperacao;
import com.example.Astrocash.models.transaction.Transaction;

import java.time.LocalDateTime;

public record DetailsTransactionDto(String id, String userId, String ativoId, TipoAtivo tipoAtivo, Double quantidade, Double precoUnitario, TipoOperacao tipoOperacao, LocalDateTime dataHora) {
    public DetailsTransactionDto(Transaction transaction){
        this(transaction.getId(), transaction.getUserId(), transaction.getAtivoId(), transaction.getTipoAtivo(), transaction.getQuantidade(), transaction.getPrecoUnitario(), transaction.getTipoOperacao(), transaction.getDataHora());
    }
}
