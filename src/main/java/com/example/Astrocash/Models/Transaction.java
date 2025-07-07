package com.example.Astrocash.Models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "transactions")
public class Transaction {

    @Id
    private Long id;
    private Long userId;
    private Long ativoId;
    private String tipoAtivo; // "STOCK" ou "CRYPTO"
    private int quantidade;
    private double precoUnitario;
    private String tipoOperacao; // "COMPRA" ou "VENDA"
    private Instant dataHora;

}