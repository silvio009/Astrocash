package com.example.Astrocash.models.stocks;

public enum BolsaDeValores {
    B3("Brasil"),
    NYSE("Estados Unidos"),
    NASDAQ("Estados Unidos"),
    BMV("México"),
    BCBA("Argentina"),
    TSX("Canadá");

    private final String pais;

    BolsaDeValores(String pais) {
        this.pais = pais;
    }

    public String getPais() {
        return pais;
    }
}