package com.example.Astrocash.Models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "cryptos")
public class Crypto {

    @Id
    private Long id;
    private String simbolo; // ex: BTC, ETH
    private String nome;
    private double precoAtual;
    private double variacaoPercentual;
}