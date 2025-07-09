package com.example.Astrocash.dto.stock;

import com.example.Astrocash.models.stocks.Mercado;
import com.example.Astrocash.models.stocks.Pais;
import com.example.Astrocash.models.stocks.Ticker;

// Usamos Double (classe) e não double (primitivo) para poder checar se veio no JSON (null ou não) no contrutor Stock
public record UpadateStockDto
        (Ticker ticker, String nome, Mercado mercado, Pais pais, Double precoAtual, Double variacaoPercentual) {
}
