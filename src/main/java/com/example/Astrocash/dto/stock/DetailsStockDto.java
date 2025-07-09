package com.example.Astrocash.dto.stock;

import com.example.Astrocash.models.stocks.Mercado;
import com.example.Astrocash.models.stocks.Pais;
import com.example.Astrocash.models.stocks.Stock;
import com.example.Astrocash.models.stocks.Ticker;

public record DetailsStockDto(String id, Ticker ticker, String nome, Mercado mercado, Pais pais, double precoAtual, double variacaoPercentual) {

    public DetailsStockDto (Stock stock){
        this(stock.getId(), stock.getTicker(), stock.getNome(), stock.getMercado(), stock.getPais(), stock.getPrecoAtual(), stock.getVariacaoPercentual());
    }
}
