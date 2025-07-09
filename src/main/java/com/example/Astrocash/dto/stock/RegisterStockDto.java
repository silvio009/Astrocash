package com.example.Astrocash.dto.stock;

import com.example.Astrocash.models.stocks.Mercado;
import com.example.Astrocash.models.stocks.Pais;
import com.example.Astrocash.models.stocks.Ticker;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record RegisterStockDto(

        @NotNull(message = "Esse campo é obrigatório")
        Ticker ticker,

        @NotNull(message = "Esse campo é obrigatório")
        String nome,

        @NotNull(message = "Esse campo é obrigatório")
        Mercado mercado,

        @NotNull(message = "Esse campo é obrigatório")
        Pais pais,

        @NotNull(message = "Esse campo é obrigatório")
        @PositiveOrZero(message = "Preço atual não pode ser negativo")
        double precoAtual,

        @DecimalMin(value = "-100.0", message = "A variação percentual mínima é -100% ")
        @DecimalMax(value = "100.0", message = "A variação percentual máxima é 100%")
        double variacaoPercentual) {
}
