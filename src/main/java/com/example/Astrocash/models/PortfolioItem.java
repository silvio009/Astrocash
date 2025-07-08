package com.example.Astrocash.models;


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
    private String id;
    private String userId; // id do usuário dono da carteira
    private String ativoId; // id do ativo (ação ou cripto)
    private String tipoAtivo; // "STOCK" ou "CRYPTO"

    private int quantidade;
    private double precoMedioCompra;

}
