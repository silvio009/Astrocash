package com.example.Astrocash.dto.transaction;

import com.example.Astrocash.models.transaction.TipoAtivo;
import com.example.Astrocash.models.transaction.TipoOperacao;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.time.Instant;

public record RegisterTransactionDto(

        @NotBlank(message = "O ID do ativo é obrigatório")
        String ativoId,

        @NotNull(message = "O tipo do ativo é obrigatório")
        TipoAtivo tipoAtivo,

        @NotNull(message = "A quantidade é obrigatória")
        @DecimalMin(value = "0.0001", message = "A quantidade deve ser maior que zero")
        Double quantidade,

        @NotNull(message = "O preço unitário é obrigatório")
        @DecimalMin(value = "0.01", message = "O preço unitário deve ser maior que zero")
        Double precoUnitario,

        @NotNull(message = "O tipo do ativo é obrigatório")
        TipoOperacao tipoOperacao

)
{
}
