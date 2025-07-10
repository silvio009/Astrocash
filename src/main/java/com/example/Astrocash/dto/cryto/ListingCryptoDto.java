package com.example.Astrocash.dto.cryto;

import com.example.Astrocash.models.crypto.Crypto;
import com.example.Astrocash.models.crypto.Simbolo;

public record ListingCryptoDto(String id, Simbolo simbolo, String nome, Double precoAtual, Double variacaoPercentual) {
    public ListingCryptoDto(Crypto crypto){
        this(crypto.getId(), crypto.getSimbolo(), crypto.getNome(), crypto.getPrecoAtual(),crypto.getVariacaoPercentual());
    }
}
