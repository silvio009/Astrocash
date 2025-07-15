package com.example.Astrocash.dto.transaction;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.time.Instant;

public record RegisterTransactionDto(
        @NotBlank(message = "O ID do usuário é obrigatório")
        String userId,

        @NotBlank(message = "O ID do ativo é obrigatório")
        String ativoId,

        @NotBlank(message = "O tipo do ativo é obrigatório")
        @Pattern(regexp = "STOCK|CRYPTO", message = "O tipo de ativo deve ser 'STOCK' ou 'CRYPTO'")
        String tipoAtivo,

        @NotNull(message = "A quantidade é obrigatória")
        @DecimalMin(value = "0.0001", message = "A quantidade deve ser maior que zero")
        Double quantidade,

        @NotNull(message = "O preço unitário é obrigatório")
        @DecimalMin(value = "0.01", message = "O preço unitário deve ser maior que zero")
        Double precoUnitario,

        @NotBlank(message = "O tipo de operação é obrigatório")
        @Pattern(regexp = "COMPRA|VENDA", message = "O tipo de operação deve ser 'COMPRA' ou 'VENDA'")
        String tipoOperacao

)
{
}
