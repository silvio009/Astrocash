package com.example.Astrocash.dto.cryto;

import com.example.Astrocash.models.crypto.Simbolo;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record RegisterCryptoDto(

        @NotNull(message = "Esse campo é obrigatório")
        Simbolo simbolo,

        @NotNull(message = "Esse campo é obrigatório")
        String nome,

        @NotNull(message = "Esse campo é obrigatório")
        @PositiveOrZero(message = "Preço atual não pode ser negativo")
        Double precoAtual,

        @NotNull(message = "Esse campo é obrigatório")
        @DecimalMin(value = "-100.0", message = "A variação percentual mínima é -100% ")
        @DecimalMax(value = "100.0", message = "A variação percentual máxima é 100%")
        Double variacaoPercentual
) {
}
