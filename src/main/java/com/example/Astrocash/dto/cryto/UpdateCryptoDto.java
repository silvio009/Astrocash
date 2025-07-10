package com.example.Astrocash.dto.cryto;

import com.example.Astrocash.models.crypto.Simbolo;

public record UpdateCryptoDto(Simbolo simbolo, String nome, Double precoAtual, Double variacaoPercentual) {
}
