package com.example.Astrocash.dto.portfolio;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record RegisterPortfolioDto(

        @NotBlank(message = "O ID do ativo é obrigatório")
        String ativoId,

        @NotBlank(message = "O tipo do ativo é obrigatório")
        @Pattern(regexp = "STOCK|CRYPTO", message = "O tipo de ativo deve ser 'STOCK' ou 'CRYPTO'")
        String tipoAtivo,

        @NotNull(message = "A quantidade é obrigatória")
        @DecimalMin(value = "0.0001", message = "A quantidade deve ser maior que zero")
        Double quantidade,

        @NotNull(message = "O preço médio de compra é obrigatório")
        @DecimalMin(value = "0.01", message = "O preço médio deve ser maior que zero")
        Double precoMedioCompra

) {}