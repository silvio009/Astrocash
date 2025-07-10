package com.example.Astrocash.dto.cryto;

import com.example.Astrocash.models.crypto.Crypto;
import com.example.Astrocash.models.crypto.Simbolo;

public record DetailsCryptoDto(String id, Simbolo simbolo, String nome, Double precoAtual, Double variacaoPercentual) {
    public DetailsCryptoDto (Crypto crypto){
        this(crypto.getId(), crypto.getSimbolo(),crypto.getNome(), crypto.getPrecoAtual(), crypto.getVariacaoPercentual());
    }
}
