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
@Document(collection = "portfolio_items")
public class PortfolioItem {

    @Id
    private Long id;
    private Long userId; // id do usuário dono da carteira
    private Long ativoId; // id do ativo (ação ou cripto)
    private String tipoAtivo; // "STOCK" ou "CRYPTO"

    private int quantidade;
    private double precoMedioCompra;

}
